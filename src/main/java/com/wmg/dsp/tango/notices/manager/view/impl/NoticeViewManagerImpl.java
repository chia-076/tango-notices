package com.wmg.dsp.tango.notices.manager.view.impl;

import com.wmg.dsp.tango.jazz.commons.bootstrap.domain.event.EventStepType;
import com.wmg.dsp.tango.jazz.commons.bootstrap.exception.response.ServerException;
import com.wmg.dsp.tango.jazz.commons.bootstrap.manager.dto.DTOBuilder;
import com.wmg.dsp.tango.jazz.commons.bootstrap.manager.dto.impl.DTOException;
import com.wmg.dsp.tango.jazz.commons.bootstrap.manager.event.dispatcher.EventStepProcessor;
import com.wmg.dsp.tango.jazz.commons.bootstrap.manager.view.impl.AbstractViewManagerImpl;
import com.wmg.dsp.tango.notices.domain.cassandra.Notice;
import com.wmg.dsp.tango.notices.domain.cassandra.NoticeView;
import com.wmg.dsp.tango.notices.domain.dto.NoticeDTO;
import com.wmg.dsp.tango.notices.manager.view.NoticeViewManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@EventStepProcessor(step = EventStepType.CONSTRUCT_VIEW, entityType = "Notice")
public class NoticeViewManagerImpl extends AbstractViewManagerImpl<Notice, NoticeView> implements NoticeViewManager {

    private static final Logger log = LoggerFactory.getLogger(NoticeViewManagerImpl.class);

    @Autowired
    private DTOBuilder dtoBuilder;

    @Override
    public NoticeView reconstructView(Notice notice) {
        NoticeDTO dto = null;

        try {
            dto = dtoBuilder.build(notice, NoticeDTO.class);
        } catch (DTOException e) {
            throw new ServerException("Unable to build NoticeDTO.", e);
        }

        return new NoticeView(notice.getId(), serializeView(dto));
    }
}
