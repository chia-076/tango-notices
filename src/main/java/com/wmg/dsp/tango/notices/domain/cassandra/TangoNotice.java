package com.wmg.dsp.tango.notices.domain.cassandra;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wmg.dsp.platform.persistence.annotations.Id;
import com.wmg.dsp.platform.persistence.cassandra.annotations.Column;
import com.wmg.dsp.platform.persistence.cassandra.annotations.ColumnFamily;
import com.wmg.dsp.tango.jazz.commons.bootstrap.domain.common.BaseParentExtEntity;
import com.wmg.dsp.tango.jazz.commons.bootstrap.manager.event.dispatcher.EventEntity;

import java.util.Date;
import java.util.UUID;

@EventEntity
@ColumnFamily(TangoNotice.COLUMN_FAMILY_NAME)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class TangoNotice extends BaseParentExtEntity {

    public static final String COLUMN_FAMILY_NAME = "tangonotice";

    public static final String EVENT_ID = "event_id";
    public static final String COMMENT = "comment";
    public static final String USER_ID = "user_id";
    public static final String USER_PROFILE = "user_profile";
    public static final String DATE = "date";
    public static final String NOTICE_TYPE = "notice_type";
    public static final String NOTICE_REASON = "notice_reason";


    @Id
    @JsonProperty
    private UUID id;

    @Column(EVENT_ID)
    @JsonProperty(EVENT_ID)
    private UUID eventId;  //from the event that created this version

    @Column(COMMENT)
    @JsonProperty(COMMENT)
    private String comment;

    private String user; // TODO: placeholder for Tango User object

    @Column(DATE)
    @JsonProperty(DATE)
    private Date date;

    @Column()
    private String noticeType; //TODO: placeholder for TangoNotice Type

    private String noticeEntity; //TODO: placeholder for TangoNotice Entity

    private String noticeReason; //TODO: placeholder for TangoNotice Reas0000000000000000on

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public Date getCreatedAt() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setCreatedAt(Date date) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public UUID getEventId() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setEventId(UUID uuid) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
