package com.wmg.dsp.tango.notices.domain.cassandra;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wmg.dsp.platform.persistence.annotations.Id;
import com.wmg.dsp.platform.persistence.cassandra.annotations.Column;
import com.wmg.dsp.platform.persistence.cassandra.annotations.ColumnFamily;
import com.wmg.dsp.tango.jazz.commons.bootstrap.util.GenericStringSerializer;
import com.wmg.dsp.tango.jazz.commons.bootstrap.util.JsonUtils;
import com.wmg.dsp.tango.jazz.commons.domain.BaseEntity;
import com.wmg.dsp.tango.jazz.commons.shared.validation.constraints.CheckDomainObject;
import com.wmg.dsp.tango.notices.domain.dto.NoticeDTO;

import java.util.UUID;

@CheckDomainObject
@ColumnFamily(NoticeView.COLUMN_FAMILY_NAME)
public class NoticeView extends BaseEntity<UUID> {
    public static final String COLUMN_FAMILY_NAME = "notice_view";

    public static final String ID = "id";
    public static final String DATA = "data";

    @Id
    @JsonProperty(ID)
    private UUID id;

    @Column(DATA)
    @JsonSerialize(using = GenericStringSerializer.class)
    private String data;

    public NoticeView() {
    }

    public NoticeView(UUID id, NoticeDTO noticeDTO) {
        this.id = id;
        this.data = JsonUtils.getValueAsString(noticeDTO);
    }

    public NoticeView(UUID id, String data) {
        this.id = id;
        this.data = data;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
