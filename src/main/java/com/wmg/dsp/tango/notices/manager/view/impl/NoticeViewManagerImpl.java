package com.wmg.dsp.tango.notices.manager.view.impl;

import com.wmg.dsp.tango.jazz.commons.bootstrap.domain.event.EventStepType;
import com.wmg.dsp.tango.jazz.commons.bootstrap.manager.event.dispatcher.EventStepProcessor;
import com.wmg.dsp.tango.jazz.commons.bootstrap.manager.view.impl.AbstractViewManagerImpl;
import com.wmg.dsp.tango.notices.domain.cassandra.Notice;
import com.wmg.dsp.tango.notices.domain.cassandra.NoticeView;
import com.wmg.dsp.tango.notices.manager.view.NoticeViewManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@EventStepProcessor(step = EventStepType.CONSTRUCT_VIEW, entityType = "Notice")
public class NoticeViewManagerImpl extends AbstractViewManagerImpl<Notice, NoticeView> implements NoticeViewManager {

    private static final Logger log = LoggerFactory.getLogger(NoticeViewManagerImpl.class);

    @Override
    public NoticeView reconstructView(Notice entity) {

        return null; //TODO: implement me
    }
}
