package com.muanza.repomultilingua.crm.api.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

@Component
public class CrmStore {
    private final AtomicLong sequence = new AtomicLong(1L);
    private final Map<Long, CrmModels.ParceiroDTO> parceiros = new ConcurrentHashMap<>();
    private final Map<Long, CrmModels.TenantDTO> tenants = new ConcurrentHashMap<>();
    private final Map<Long, CrmModels.LicencaDTO> licencas = new ConcurrentHashMap<>();

    public AtomicLong getSequence() {
        return sequence;
    }

    public Map<Long, CrmModels.ParceiroDTO> getParceiros() {
        return parceiros;
    }

    public Map<Long, CrmModels.TenantDTO> getTenants() {
        return tenants;
    }

    public Map<Long, CrmModels.LicencaDTO> getLicencas() {
        return licencas;
    }
}

