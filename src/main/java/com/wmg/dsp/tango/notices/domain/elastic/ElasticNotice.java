package com.wmg.dsp.tango.notices.domain.elastic;

import com.wmg.dsp.platform.persistence.annotations.Id;
import com.wmg.dsp.platform.persistence.elasticsearch.annotations.IndexedDocument;
import com.wmg.dsp.platform.persistence.elasticsearch.annotations.IndexedProperty;
import com.wmg.dsp.tango.jazz.commons.domain.BaseEntity;
import com.wmg.dsp.tango.jazz.commons.domain.ESPropertyType;
import com.wmg.dsp.tango.jazz.commons.domain.IndexedPropertyType;
import com.wmg.dsp.tango.jazz.commons.utils.Utils;
import com.wmg.dsp.tango.notices.domain.cassandra.Notice;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.UUID;

@IndexedDocument(ElasticNotice.DOCUMENT)
public class ElasticNotice extends BaseEntity<UUID> {
    public final static String DOCUMENT = "notice";
    public static final String RAW_NOTICE = "raw_notice";

    @Id
    @IndexedProperty(Utils.ID)
    private UUID id;

    @IndexedProperty(RAW_NOTICE)
    @IndexedPropertyType(ESPropertyType.String)
    private String rawNotice;

    public ElasticNotice() {
    }

    public ElasticNotice(Notice notice) {
        if (notice == null) {
            throw new IllegalArgumentException("Notice required when creating ElasticNotice");
        }

        this.id = notice.getId();
    }

    public ElasticNotice(Notice notice, String rawNotice) {
        this(notice);

        this.rawNotice = rawNotice;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID uuid) {
        this.id = uuid;
    }

    public String getRawNotice() {
        return rawNotice;
    }

    public void setRawNotice(String rawNotice) {
        this.rawNotice = rawNotice;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Notice)) {
            return false;
        }

        ElasticNotice that = (ElasticNotice) obj;
        return new EqualsBuilder()
                .append(this.id, that.id)
                .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .toString();
    }
}
