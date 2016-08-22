package com.wmg.dsp.tango.notices.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseEntity<K> {

    public abstract K getId();

    public abstract void setId(K id);
}

