package com.wmg.dsp.tango.notices.repositories.cassandra;

import com.wmg.dsp.tango.jazz.commons.bootstrap.manager.repo.ExtBaseDataRepository;
import com.wmg.dsp.tango.notices.domain.cassandra.Notice;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class NoticeDataRepository extends ExtBaseDataRepository<Notice, UUID> {

    public NoticeDataRepository(Class<Notice> entityClass) {
        super(entityClass);
    }

    public Notice save(Notice notices) throws Exception {
        return update(notices);
    }
}
