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
@ColumnFamily(Notice.COLUMN_FAMILY_NAME)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Notice extends BaseParentExtEntity {

    public static final String COLUMN_FAMILY_NAME = "tangonotice";

    public static final String EVENT_ID = "event_id";
    public static final String COMMENT = "comment";
    public static final String USER_NAME = "user_name";
    public static final String USER_PROFILE_ID = "user_profile_id";
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

    @JsonProperty(USER_NAME)
    private String userName;

    @Column(USER_PROFILE_ID)
    @JsonProperty(USER_PROFILE_ID)
    private UUID userProfileId;

    @Column(DATE)
    @JsonProperty(DATE)
    private Date date;

    private String noticeType; //TODO: placeholder for Notice Type
    private String noticeEntity; //TODO: placeholder for Notice Entity
    private String noticeReason; //TODO: placeholder for Notice Reas0000000000000000on

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
