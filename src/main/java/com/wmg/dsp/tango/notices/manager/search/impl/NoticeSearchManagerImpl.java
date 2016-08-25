package com.wmg.dsp.tango.notices.manager.search.impl;

import com.wmg.dsp.tango.jazz.commons.bootstrap.domain.event.EventStepType;
import com.wmg.dsp.tango.jazz.commons.bootstrap.manager.event.dispatcher.EventStepProcessor;
import com.wmg.dsp.tango.jazz.commons.bootstrap.manager.search.impl.AbstractGenericSearchManagerImpl;
import com.wmg.dsp.tango.jazz.commons.search.ranking.RankingQueryBuilder;
import com.wmg.dsp.tango.notices.domain.cassandra.Notice;
import com.wmg.dsp.tango.notices.domain.dto.NoticeDTO;
import com.wmg.dsp.tango.notices.domain.elastic.ElasticNotice;
import com.wmg.dsp.tango.notices.manager.search.NoticeSearchManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@EventStepProcessor(step = EventStepType.CONSTRUCT_VIEW, entityType = {"Notice"})
public class NoticeSearchManagerImpl extends AbstractGenericSearchManagerImpl<NoticeDTO, Notice, ElasticNotice> implements NoticeSearchManager {
    private static final Logger log = LoggerFactory.getLogger(NoticeSearchManagerImpl.class);

    public NoticeSearchManagerImpl() {
        super(NoticeDTO.class, Notice.class, ElasticNotice.class);
    }

    @Override
    public RankingQueryBuilder getRankingQueryBuilder() {
        //TODO: ranking query builder implementation
        return null;
    }

    @Override
    protected String getRawFieldName() {
        return ElasticNotice.RAW_NOTICE;
    }
}
