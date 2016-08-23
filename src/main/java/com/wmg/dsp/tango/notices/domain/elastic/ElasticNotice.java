package com.wmg.dsp.tango.notices.domain.elastic;

import com.wmg.dsp.platform.persistence.elasticsearch.annotations.IndexedDocument;
import com.wmg.dsp.tango.jazz.commons.domain.BaseEntity;

import java.util.UUID;

//TODO: implement me
@IndexedDocument(ElasticNotice.DOCUMENT)
public class ElasticNotice extends BaseEntity<UUID> {
    public final static String DOCUMENT = "notice";

    @Override
    public UUID getId() {
        return null;
    }

    @Override
    public void setId(UUID uuid) {

    }
}
