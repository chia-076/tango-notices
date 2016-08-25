package com.wmg.dsp.tango.notices.repositories.search;

import com.wmg.dsp.tango.jazz.commons.search.ESBaseDataRepository;
import com.wmg.dsp.tango.notices.domain.elastic.ElasticNotice;
import org.springframework.stereotype.Component;

@Component
public class NoticeElasticSearchRepository extends ESBaseDataRepository<ElasticNotice> {
    public static final String INDEX_ALIAS = "notices";
    public static final String MAPPING_FILE_PATH = "scripts/elasticsearch/notices/notices-mapping.json";

    public NoticeElasticSearchRepository() {
        this.setIndex(INDEX_ALIAS);
        this.setMappingPath(MAPPING_FILE_PATH);
    }
}
