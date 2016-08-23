package com.wmg.dsp.tango.notices.manager.search;

import com.wmg.dsp.tango.jazz.commons.bootstrap.manager.search.GenericSearchManager;
import com.wmg.dsp.tango.notices.domain.cassandra.Notice;
import com.wmg.dsp.tango.notices.domain.dto.NoticeDTO;
import org.springframework.stereotype.Component;

@Component
public interface NoticeSearchManager extends GenericSearchManager<NoticeDTO, Notice> {
}
