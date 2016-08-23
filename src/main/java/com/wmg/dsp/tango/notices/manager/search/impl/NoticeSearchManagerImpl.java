package com.wmg.dsp.tango.notices.manager.search.impl;

import com.wmg.dsp.tango.jazz.commons.bootstrap.manager.search.impl.AbstractGenericSearchManagerImpl;
import com.wmg.dsp.tango.jazz.commons.search.ranking.RankingQueryBuilder;
import com.wmg.dsp.tango.notices.domain.cassandra.Notice;
import com.wmg.dsp.tango.notices.domain.dto.NoticeDTO;
import com.wmg.dsp.tango.notices.domain.elastic.ElasticNotice;
import com.wmg.dsp.tango.notices.manager.search.NoticeSearchManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//TODO: implement me
@Component
public class NoticeSearchManagerImpl extends AbstractGenericSearchManagerImpl<NoticeDTO, Notice, ElasticNotice> implements NoticeSearchManager {
    private static final Logger log = LoggerFactory.getLogger(NoticeSearchManagerImpl.class);

    public NoticeSearchManagerImpl() {
        super(NoticeDTO.class, Notice.class, ElasticNotice.class);
    }

    @Override
    protected String getRawFieldName() {
        return null;
    }

    @Override
    public RankingQueryBuilder getRankingQueryBuilder() {
        return null;
    }
}
