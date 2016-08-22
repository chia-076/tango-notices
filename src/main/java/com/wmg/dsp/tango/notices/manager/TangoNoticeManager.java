package com.wmg.dsp.tango.notices.manager;

import com.wmg.dsp.tango.notices.domain.cassandra.TangoNotice;
import com.wmg.dsp.tango.notices.domain.cassandra.TangoNoticeView;
import com.wmg.dsp.tango.notices.exception.response.BusinessValidationException2;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TangoNoticeManager {
    private static final Logger log = LoggerFactory.getLogger(TangoNoticeManager.class);

    @Autowired
    private PersistenceManager persistenceManager;

    public TangoNoticeView getTangoNoticeById(UUID id) {
        try {
            return null;

        } catch (Exception e) {
            log.error("Error getting entity by id: " + id, e);
            throw new BusinessValidationException2("An error occurred getting entity with id: " + id, e);
        }
    }

    public Collection<TangoNotice> saveTestEntities(List<TangoNotice> testEntities) {
        try {
            Map<UUID, TangoNotice> savedEntities = new HashMap<>();

            if (savedEntities != null && savedEntities.values() != null) {
                return savedEntities.values();
            }
        } catch (Exception e) {
            log.error("Error saving entities", e);
            throw new BusinessValidationException2(
                    "Error saving entities with ids: " + StringUtils.join(extractIds(testEntities), ", "), e);
        }

        return null;
    }

    private Set<UUID> extractIds(List<TangoNotice> testEntities) {
        Set<UUID> ids = new HashSet<>();
        for (TangoNotice tangoNotice : testEntities) {
            ids.add(tangoNotice.getId());
        }

        return ids;
    }

}
