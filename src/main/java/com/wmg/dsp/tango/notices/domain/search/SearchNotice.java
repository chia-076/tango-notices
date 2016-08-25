package com.wmg.dsp.tango.notices.domain.search;

import com.wmg.dsp.platform.persistence.annotations.Id;
import com.wmg.dsp.platform.persistence.elasticsearch.annotations.IndexedDocument;
import com.wmg.dsp.platform.persistence.elasticsearch.annotations.IndexedProperty;

import java.util.UUID;

@IndexedDocument(SearchNotice.DOCUMENT)
public class SearchNotice {
    public static final String DOCUMENT = "notice";

    public static final String EVENT_ID = "event_id";
    public static final String COMMENT = "comment";
    public static final String USER_NAME = "user_name";
    public static final String USER_PROFILE_ID = "user_profile_id";
    public static final String NOTICE_ENTITY_CODE = "notice_entity_code";
    public static final String NOTICE_TYPE_CODE = "notice_type_code";
    public static final String NOTICE_REASON_CODE = "notice_reason_code";
    public static final String CREATED_AT = "created_at";

    @Id
    private UUID id;

    @IndexedProperty(COMMENT)
    private String comment;

    @IndexedProperty(USER_NAME)
    private String userName;

    @IndexedProperty(USER_PROFILE_ID)
    private UUID userProfileId;

    @IndexedProperty(NOTICE_TYPE_CODE)
    private String noticeTypeCode;

    @IndexedProperty(NOTICE_ENTITY_CODE)
    private String noticeEntityCode;
}
