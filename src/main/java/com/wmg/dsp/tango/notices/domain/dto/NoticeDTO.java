package com.wmg.dsp.tango.notices.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wmg.dsp.tango.notices.domain.cassandra.Notice;

import java.util.Date;
import java.util.UUID;

import static com.fasterxml.jackson.databind.annotation.JsonSerialize.*;

@JsonSerialize(include = Inclusion.NON_NULL)
public class NoticeDTO {

    public static final String COMMENT = "comment";
    public static final String USER_NAME = "user_name";
    public static final String USER_PROFILE_ID = "user_profile_id";
    public static final String NOTICE_ENTITY_CODE = "notice_entity_code";
    public static final String NOTICE_TYPE_CODE = "notice_type_code";
    public static final String NOTICE_REASON_CODE = "notice_reason_code";
    public static final String CREATED_AT = "created_at";

    @JsonProperty
    private UUID id;

    @JsonProperty(USER_NAME)
    private String userName;

    @JsonProperty(USER_PROFILE_ID)
    private UUID userProfileId;

    @JsonProperty(COMMENT)
    private String comment;

    @JsonProperty(NOTICE_ENTITY_CODE)
    private String noticeEntityCode;

    @JsonProperty(NOTICE_TYPE_CODE)
    private String noticeTypeCode;

    @JsonProperty(NOTICE_REASON_CODE)
    private String noticeReasonCode;

    @JsonProperty(CREATED_AT)
    private Date date;

    public NoticeDTO() {
    }

    public NoticeDTO(Notice notice) {
        this.id = notice.getId();
        this.userName = notice.getUserName();
        this.userProfileId = notice.getUserProfileId();
        this.comment = notice.getComment();
        this.noticeEntityCode = notice.getNoticeEntityCode();
        this.noticeTypeCode = notice.getNoticeTypeCode();
        this.noticeReasonCode = notice.getNoticeReasonCode();
        this.date = notice.getCreatedAt();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public UUID getUserProfileId() { return userProfileId; }

    public void setUserProfileId(UUID userProfileId) { this.userProfileId = userProfileId; }

    public String getComment() { return comment; }

    public void setComment(String comment) { this.comment = comment; }

    public String getNoticeEntityCode() { return noticeEntityCode; }

    public void setNoticeEntityCode(String noticeEntityCode) { this.noticeEntityCode = noticeEntityCode; }

    public String getNoticeTypeCode() { return noticeTypeCode; }

    public void setNoticeTypeCode(String noticeTypeCode) { this.noticeTypeCode = noticeTypeCode; }

    public String getNoticeReasonCode() { return noticeReasonCode; }

    public void setNoticeReasonCode(String noticeReasonCode) { this.noticeReasonCode = noticeReasonCode; }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public String toString() {
        return "NoticeDTO{" +
                "id=" + id +
                ", userName=" + userName +
                ", userProfileId=" + userProfileId +
                ", comment=" + comment +
                ", noticeEntityCode=" + noticeEntityCode +
                ", noticeTypeCode=" + noticeTypeCode +
                ", noticeReasonCode=" + noticeReasonCode +
                ", createdAt=" + date +
                "}";
    }
}
