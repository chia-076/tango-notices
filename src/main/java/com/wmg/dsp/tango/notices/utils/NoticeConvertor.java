package com.wmg.dsp.tango.notices.utils;

import com.wmg.dsp.tango.jazz.commons.bootstrap.converter.AbstractConverter;
import com.wmg.dsp.tango.jazz.commons.bootstrap.converter.Converter;
import com.wmg.dsp.tango.notices.domain.cassandra.Notice;
import org.springframework.stereotype.Component;

@Component
public class NoticeConvertor extends AbstractConverter<Notice> implements Converter<Notice> {
    public NoticeConvertor() {
        super(Notice.class);
    }
}
