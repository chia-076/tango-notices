package com.wmg.dsp.tango.notices.domain.cassandra;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wmg.dsp.platform.persistence.annotations.Id;
import com.wmg.dsp.platform.persistence.cassandra.annotations.Column;
import com.wmg.dsp.platform.persistence.cassandra.annotations.ColumnFamily;
import com.wmg.dsp.tango.jazz.commons.bootstrap.domain.common.BaseParentExtEntity;
import com.wmg.dsp.tango.jazz.commons.bootstrap.manager.event.dispatcher.EventEntity;
import com.wmg.dsp.tango.jazz.commons.bootstrap.util.JsonDateTimeSerializer;

import java.util.Date;
import java.util.UUID;

@EventEntity
@ColumnFamily(Notice.COLUMN_FAMILY_NAME)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Notice extends BaseParentExtEntity {

    public static final String COLUMN_FAMILY_NAME = "tangonotice";

    public static final String EVENT_ID = "event_id";
    public static final String COMMENT = "comment";
    public static final String USER_NAME = "user_name";
    public static final String USER_PROFILE_ID = "user_profile_id";
    public static final String NOTICE_ENTITY_CODE = "notice_entity_code";
    public static final String NOTICE_TYPE_CODE = "notice_type_code";
    public static final String NOTICE_REASON_CODE = "notice_reason_code";
    public static final String CREATED_AT = "created_at";

    @Id
    @JsonProperty
    private UUID id;

    @Column(EVENT_ID)
    @JsonProperty(EVENT_ID)
    private UUID eventId;  //from the event that created this version

    @Column(COMMENT)
    @JsonProperty(COMMENT)
    private String comment;

    @JsonProperty(USER_NAME)
    private String userName;

    @Column(USER_PROFILE_ID)
    @JsonProperty(USER_PROFILE_ID)
    private UUID userProfileId;

    @Column(NOTICE_TYPE_CODE)
    @JsonProperty(NOTICE_TYPE_CODE)
    private String noticeTypeCode;

    @Column(NOTICE_ENTITY_CODE)
    @JsonProperty(NOTICE_ENTITY_CODE)
    private String noticeEntityCode;

    @Column(NOTICE_REASON_CODE)
    @JsonProperty(NOTICE_REASON_CODE)
    private String noticeReasonCode;

    @Column(CREATED_AT)
    @JsonProperty(CREATED_AT)
    @JsonSerialize(using = JsonDateTimeSerializer.class)
    private Date date;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UUID getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(UUID userProfileId) {
        this.userProfileId = userProfileId;
    }

    public String getNoticeTypeCode() {
        return noticeTypeCode;
    }

    public void setNoticeTypeCode(String noticeTypeCode) {
        this.noticeTypeCode = noticeTypeCode;
    }

    public String getNoticeEntityCode() {
        return noticeEntityCode;
    }

    public void setNoticeEntityCode(String noticeEntityCode) {
        this.noticeEntityCode = noticeEntityCode;
    }

    public String getNoticeReasonCode() {
        return noticeReasonCode;
    }

    public void setNoticeReasonCode(String noticeReasonCode) {
        this.noticeReasonCode = noticeReasonCode;
    }

    @Override
    public Date getCreatedAt() {
        return date;
    }

    @Override
    public void setCreatedAt(Date date) {
        this.date = date;
    }

    @Override
    public UUID getEventId() {
        return null;  // TODO: fix me
    }

    @Override
    public void setEventId(UUID uuid) {
        //TODO: fix me
    }
}
