package com.wmg.dsp.tango.notices.manager.externalservices;

import com.wmg.dsp.tango.du.commons.domain.admin.Admin;
import com.wmg.dsp.tango.jazz.commons.bootstrap.manager.externalservices.ExternalEntityDataSource;

import java.util.UUID;

public interface AdminDataSource extends ExternalEntityDataSource<Admin, UUID> {
    String NAME = "ADMIN";
}
