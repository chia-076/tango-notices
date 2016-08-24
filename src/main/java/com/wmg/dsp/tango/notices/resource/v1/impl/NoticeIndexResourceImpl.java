package com.wmg.dsp.tango.notices.resource.v1.impl;

import com.wmg.dsp.tango.jazz.commons.bootstrap.exception.response.ServerException;
import com.wmg.dsp.tango.jazz.commons.bootstrap.manager.dto.DTOBuilder;
import com.wmg.dsp.tango.jazz.commons.bootstrap.manager.dto.impl.DTOException;
import com.wmg.dsp.tango.jazz.commons.bootstrap.resource.v1.impl.AbstractIndexResourceImpl;
import com.wmg.dsp.tango.jazz.commons.bootstrap.util.JsonUtils;
import com.wmg.dsp.tango.jazz.commons.domain.BaseEntity;
import com.wmg.dsp.tango.notices.domain.cassandra.Notice;
import com.wmg.dsp.tango.notices.domain.dto.NoticeDTO;
import com.wmg.dsp.tango.notices.domain.elastic.ElasticNotice;
import com.wmg.dsp.tango.notices.repositories.cassandra.NoticeDataRepository;
import com.wmg.dsp.tango.notices.resource.v1.NoticeIndexResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;

@Component("indexResource")
@Path(NoticeIndexResource.BASE_PATH)
public class NoticeIndexResourceImpl extends AbstractIndexResourceImpl<Notice, ElasticNotice> implements NoticeIndexResource {

    private static final Logger log = LoggerFactory.getLogger(NoticeIndexResourceImpl.class);

    @Autowired
    private NoticeDataRepository noticeDataRepository;

    @Autowired
    private DTOBuilder dtoBuilder;

    public NoticeIndexResourceImpl() { super(Notice.class, ElasticNotice.class);
    }

    @Override
    protected BaseEntity createElasticEntity(Object o) {
        if (o instanceof Notice) {
            try {
                NoticeDTO dto = dtoBuilder.build((Notice) o, NoticeDTO.class);
                String rawDTO = JsonUtils.getValueAsString(dto);
                return new ElasticNotice((Notice) o, rawDTO);
            } catch (DTOException e) {
                throw new ServerException("Unable to build Elastic Notice entity", e);
            }
        }
        return null;
    }
}

