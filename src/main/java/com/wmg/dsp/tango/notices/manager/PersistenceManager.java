package com.wmg.dsp.tango.notices.manager;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.wmg.dsp.platform.persistence.cassandra.annotations.ColumnFamily;
import com.wmg.dsp.platform.persistence.cassandra.messages.entities.QueryEntities;
import com.wmg.dsp.platform.persistence.cassandra.providers.CassandraProvider;
import com.wmg.dsp.platform.persistence.messages.*;
import com.wmg.dsp.platform.persistence.security.MessageSignatureService;
import com.wmg.dsp.tango.notices.domain.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Component("persistence-manager")
public class PersistenceManager {

    @Autowired
    private CassandraProvider provider;

    @Autowired
    private MessageSignatureService messageSignatureService;

    public <T extends BaseEntity> Collection<T> getByIds(Class<T> entityClass, Collection<UUID> ids) throws Exception {
        Collection<T> items = Collections.emptyList();

        if (!CollectionUtils.isEmpty(ids)) {
            GetEntities<T, UUID> request = new GetEntities<>(entityClass, ids);
            messageSignatureService.sign(request, SecurityContextHolder.getContext());

            EntitiesMessage<T, UUID> result = (EntitiesMessage<T, UUID>) provider.execute(request);
            if (!CollectionUtils.isEmpty(result.entities.values())) {
                items = result.entities.values();
            }
        }
        return items;
    }

    public <T extends BaseEntity> T getById(Class<T> entityClass, UUID id) throws Exception {
        GetEntity<T, UUID> request = new GetEntity<>(entityClass, id);
        messageSignatureService.sign(request, SecurityContextHolder.getContext());

        EntityMessage<T, UUID> result = (EntityMessage<T, UUID>) provider.execute(request);
        return result.entity;
    }

    public <T extends BaseEntity> Collection<T> getByIndexedField(Class<T> entityClass, String fieldName, Object value,
                                                                  int limit) throws Exception {
        Select query = QueryBuilder.select().from(extractColumnFamilyName(entityClass));
        if (fieldName != null && value != null) {
            query = query.where(QueryBuilder.eq(fieldName, value)).limit(limit);
        } else {
            query = query.limit(limit);
        }
        QueryEntities<T> request = new QueryEntities<>(entityClass, query);
        messageSignatureService.sign(request, SecurityContextHolder.getContext());

        EntitiesMessage<T, ?> result = (EntitiesMessage<T, ?>) provider.execute(request);
        return result.entities.values();
    }

    private <T extends BaseEntity> String extractColumnFamilyName(Class<T> entityClass) {
        return entityClass.getAnnotation(ColumnFamily.class).value();
    }

    public <T extends BaseEntity> T save(T item) throws Exception {
        SaveEntity<T> request = new SaveEntity<>(item);
        messageSignatureService.sign(request, SecurityContextHolder.getContext());

        SavedEntityMessage<T, UUID> result = (SavedEntityMessage<T, UUID>) provider.execute(request);
        return result.entity;
    }

    public <T extends BaseEntity> Map<UUID, T> saveList(List<T> items) throws Exception {
        SaveEntities<T> request = new SaveEntities<>(items);
        messageSignatureService.sign(request, SecurityContextHolder.getContext());

        SavedEntitiesMessage<T, UUID> result = (SavedEntitiesMessage<T, UUID>) provider.execute(request);
        return result.entities;
    }

    public <T> void deleteById(Class<T> entityClass, UUID id) throws Exception {
        DeleteEntity<T, UUID> request = new DeleteEntity<>(entityClass, id);
        messageSignatureService.sign(request, SecurityContextHolder.getContext());

        provider.execute(request);
    }
}
