package com.agt.crm.repository;

import com.agt.crm.entity.LicencaEntity;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class LicencaRepository {
    private final Map<String, LicencaEntity> licencas = new HashMap<>();

    public void save(String tenantCodigo, LicencaEntity entity) {
        licencas.put(tenantCodigo, entity);
    }

    public LicencaEntity findByTenantCodigo(String tenantCodigo) {
        return licencas.get(tenantCodigo);
    }
}
