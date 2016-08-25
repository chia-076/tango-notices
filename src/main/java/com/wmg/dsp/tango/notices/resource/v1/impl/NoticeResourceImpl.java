package com.wmg.dsp.tango.notices.resource.v1.impl;

import com.wmg.dsp.tango.jazz.commons.bootstrap.exception.response.ServerException;
import com.wmg.dsp.tango.notices.domain.cassandra.Notice;
import com.wmg.dsp.tango.notices.repositories.cassandra.NoticeDataRepository;
import com.wmg.dsp.tango.notices.resource.v1.NoticeResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Component
@Scope("request")
@Path(NoticeResource.BASE_PATH)
public class NoticeResourceImpl implements NoticeResource {

    private static final Logger log = LoggerFactory.getLogger(NoticeResourceImpl.class);

    @Autowired
    NoticeDataRepository noticeDataRepository;

    @Override
    public Response getNoticeById(UUID id, String userToken) {
        Notice result;
        try {
            result = noticeDataRepository.getById(id);
        } catch (Exception e) {
            throw new ServerException("Error reading Notice id=" + id, e);
        }

        if (result == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(result).build();
    }

    @Override
    public Response getPersonsByIds(List<UUID> ids, String userToken) {
        Collection<Notice> results;
        try {
            results = noticeDataRepository.getByIds(ids);
        } catch (Exception e) {
            throw new ServerException("Error reading Notices by ids=" + ids, e);
        }

        if (results == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(results).build();
    }
}
