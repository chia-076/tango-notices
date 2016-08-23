package com.wmg.dsp.tango.notices.domain.search;

import com.wmg.dsp.platform.persistence.elasticsearch.annotations.IndexedDocument;

@IndexedDocument(SearchNotice.DOCUMENT)
public class SearchNotice {
    public static final String DOCUMENT = "notice";
}
