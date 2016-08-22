package com.wmg.dsp.tango.notices.manager.externalservices.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PreDestroy;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;

import com.wmg.dsp.tango.notices.entrypoint.usertoken.UserTokenHolder;
import com.wmg.dsp.tango.notices.exception.response.ExternalServiceException2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractExternalServices {

	private static final Logger log = LoggerFactory.getLogger(AbstractExternalServices.class);
	private Client client;

	protected abstract ServiceConnection getConnection(String key);

	public Response get(String type, String endPoint, Map<String, String> pathParams, Map<String, String> params,
			Response.StatusType expectedStatus) {
		Response response;

		ServiceConnection conn = getConnection(type);

		if (conn != null) {
			String apiURI;
			try {
				apiURI = conn.getApiPath();
				if (null != endPoint) {
					apiURI += "/" + endPoint;
				}
				log.debug("Query Get Data <" + params + "> To: " + apiURI);

				WebTarget target = buildTarget(apiURI);

				for (String key : params.keySet()) {
					target = target.queryParam(key, params.get(key));
				}

				if (pathParams != null) {
					for (String pathParamName : pathParams.keySet()) {
						target = target.resolveTemplate(pathParamName, pathParams.get(pathParamName));
					}
				}

				Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON_TYPE).accept(
						MediaType.APPLICATION_JSON);

				setUserTokenHeader(invocationBuilder);

				response = invocationBuilder.get(Response.class);

			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new ExternalServiceException2(e);
			}

			if (null != response) {
				if (response.getStatus() != expectedStatus.getStatusCode()) {
					logWrongClientResponseStatus(expectedStatus, response.getStatusInfo(), apiURI, params);
					int statusCode = response.getStatus();
					throw new ExternalServiceException2("Failed External Service Query Get Service : HTTP error code : "
							+ response.getStatus() + ", return value : " + response.readEntity(String.class),
							response.getStatus(), Response.Status.fromStatusCode(statusCode));
				}
			}
		} else {
			throw new ExternalServiceException2("No service connection defined for: " + type);
		}

		return response;
	}

	public Response postJsonParam(String type, String endPoint, Object jsonParam, List<Response.Status> expectedStatus) {
		return postJsonParam(type, endPoint, jsonParam, null, expectedStatus);
	}

	public Response postJsonParam(String type, String endPoint, Object jsonParam, Map<String, String> params,
			List<Response.Status> expectedStatus) {
		ServiceConnection conn = getConnection(type);
		Response response;

		if (conn != null) {
			String apiURI;
			String jsonData;
			try {
				if (jsonParam == null) {
					throw new ExternalServiceException2("Failed External Service - " + type
							+ ": The given object does not contain any data");
				}

				ObjectMapper mapper = new ObjectMapper();
				jsonData = mapper.writeValueAsString(jsonParam);

				apiURI = conn.getApiPath();
				if (null != endPoint)
					apiURI += "/" + endPoint;

				log.debug("Post Json Data <" + jsonData + "> To: " + apiURI);

				WebTarget target = buildTarget(apiURI);

				if (params != null) {
					for (String key : params.keySet()) {
						target = target.queryParam(key, params.get(key));
					}
				}

				Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON_TYPE).accept(
						MediaType.APPLICATION_JSON);

				setUserTokenHeader(invocationBuilder);

				response = invocationBuilder.post(Entity.entity(jsonData, MediaType.APPLICATION_JSON), Response.class);

			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new ExternalServiceException2(e);
			}

			if (null != response) {
				List<Integer> statusCodes = getStatusCodes(expectedStatus);
				if (!statusCodes.contains(response.getStatus())) {
					if (jsonData.length() > 1000)
						jsonData = jsonData.substring(1000) + " ...";
					logWrongClientResponseStatus(expectedStatus, response.getStatusInfo(), apiURI, jsonData, null, null);
					int statusCode = response.getStatus();
					throw new ExternalServiceException2("Failed External Service Query Get Service : HTTP error code : "
							+ response.getStatus() + ", return value : " + response.readEntity(String.class),
							response.getStatus(), Response.Status.fromStatusCode(statusCode));
				}
			}
		} else {
			throw new ExternalServiceException2("No service connection defined for " + type);
		}

		return response;
	}

	public <T> T getEntityById(String type, String endPoint, Class<T> objectClass, UUID entityId) {

		if (objectClass == null) {
			log.error("Domain object type for GetEntityById operation not specified");
			throw new ExternalServiceException2("Domain object type for GetEntityById operation not specified");
		}

		Map<String, String> pathParams = new HashMap<>();
		pathParams.put("id", entityId.toString());

		Response response = get(type, endPoint, pathParams, new HashMap<String, String>(), Response.Status.OK);

		return response.readEntity(objectClass);
	}

	private void setUserTokenHeader(Invocation.Builder invocationBuilder) {
		String userToken = UserTokenHolder.get();

		if (userToken != null) {
			log.debug("Sending header: <Authorization: " + userToken + ">");
			invocationBuilder.header(ServiceConstants.DEFAULT_AUTHORIZATION, userToken);
		}
	}

	private WebTarget buildTarget(String apiURI) {

		if (client == null) {
			synchronized (this) {
				if (client == null) {
					client = ClientBuilder.newClient();
				}
			}
		}

		return client.target(apiURI);
	}

	@PreDestroy
	public void destroyClient() {
		client.close();
	}

	private List<Integer> getStatusCodes(List<Status> expectedStatus) {
		List<Integer> statusCodes = new ArrayList<>();
		if (!CollectionUtils.isEmpty(expectedStatus)) {
			for (Status status : expectedStatus) {
				statusCodes.add(status.getStatusCode());
			}
		}

		return statusCodes;
	}

	private void logWrongClientResponseStatus(List<Response.Status> expectedStatuses, StatusType statusType,
			String apiURI, Object body, List<Cookie> cookies, Map<String, Object> headers) {

		String expectedStatus = "[";
		if (!CollectionUtils.isEmpty(expectedStatuses)) {
			int i = 0;
			for (Response.Status expected : expectedStatuses) {
				expectedStatus += i > 0 ? ", " : "";
				expectedStatus += getStatusDescription(expected);
				i++;
			}
		}
		expectedStatus += "]";

		log.error("Wrong clientResponseStatus received from URL {}, body {}, token {}, cookies {}, headers {}"
				+ "expected status {} but received {} ", apiURI, body, UserTokenHolder.get(), cookies, headers,
				expectedStatus, getStatusDescription(statusType));
	}

	private void logWrongClientResponseStatus(Response.StatusType expectedStatus, Response.StatusType responseStatus,
			String apiURI, Map<?, ?> params) {
		log.error(
				"Wrong clientResponseStatus received from URL {}, parameters {}, token {}, expected status {} but received {} ",
				apiURI, params, UserTokenHolder.get(), this.getStatusDescription(expectedStatus),
				this.getStatusDescription(responseStatus));
	}

	private String getStatusDescription(Response.StatusType status) {
		return status != null ? status.getReasonPhrase() + " (" + status.getStatusCode() + ")" : "null";
	}

}
