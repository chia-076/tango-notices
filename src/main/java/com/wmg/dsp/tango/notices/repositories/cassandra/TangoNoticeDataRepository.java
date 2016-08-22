package com.wmg.dsp.tango.notices.repositories.cassandra;

import com.wmg.dsp.tango.jazz.commons.bootstrap.manager.repo.ExtBaseDataRepository;
import com.wmg.dsp.tango.notices.domain.cassandra.TangoNotice;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TangoNoticeDataRepository extends ExtBaseDataRepository<TangoNotice, UUID> {

    public TangoNoticeDataRepository(Class<TangoNotice> entityClass) {
        super(entityClass);
    }

    public TangoNotice save(TangoNotice tangoNotices) throws Exception {
        return update(tangoNotices);
    }
}
