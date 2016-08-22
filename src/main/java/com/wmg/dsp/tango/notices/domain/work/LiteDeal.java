package com.wmg.dsp.tango.notices.domain.work;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LiteDeal {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("brief_number")
    private String briefNumber;

    @JsonProperty("deal_name")
    private String dealName;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBriefNumber() {
        return briefNumber;
    }

    public void setBriefNumber(String briefNumber) {
        this.briefNumber = briefNumber;
    }

    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

}
