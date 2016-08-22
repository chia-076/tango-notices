package com.wmg.dsp.tango.notices.manager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.wmg.dsp.tango.notices.domain.work.LiteDeal;
import com.wmg.dsp.tango.notices.exception.response.BusinessValidationException2;
import com.wmg.dsp.tango.notices.exception.response.ExternalServiceException2;
import com.wmg.dsp.tango.notices.manager.externalservices.ExternalServices;

@Component
public class DealManager {

    private static final String GET_CP_DEALS_ENDPOINT = "deals/lite_deal/by_ids";

    @Autowired
    private ExternalServices externalServices;

    public Map<String, String> getDealInfo(Map<UUID, String> dealIdBriefNumbersMap, Set<UUID> dealIds) {

        Map<String, String> briefNumbersMap = new HashMap<>();

        try {
            Response response = externalServices.postJsonParam(ExternalServices.Names.DEALS.toString(),
                    GET_CP_DEALS_ENDPOINT, dealIds, Arrays.asList(Response.Status.OK));

            List<LiteDeal> deals = response.readEntity(new GenericType<List<LiteDeal>>() {
            });

            if (!CollectionUtils.isEmpty(deals)) {
                for (LiteDeal deal : deals) {
                    briefNumbersMap.put(deal.getBriefNumber(), deal.getDealName());
                    dealIdBriefNumbersMap.put(deal.getId(), deal.getBriefNumber());
                }
            }
        } catch (ExternalServiceException2 e) {
            if (!Response.Status.NOT_FOUND.equals(e.getStatus())) {
                throw new BusinessValidationException2("An error occurred while searching for Deal Info "
                        + StringUtils.join(dealIdBriefNumbersMap.keySet(), ","), e);
            }
        }

        return briefNumbersMap;
    }

}
