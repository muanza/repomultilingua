package com.agt.crm.repository;

import com.agt.crm.entity.LicencaEntity;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@ApplicationScoped
public class LicencaRepository {
    private final AtomicLong sequence = new AtomicLong(1);
    private final List<LicencaEntity> licencas = new ArrayList<>();

    public LicencaRepository() {
        save(seed("TENANT-001", 1L, "AGT-PRO", "ATIVA", LocalDate.now().plusDays(365), 1000, "LIC-AGT-001"));
        save(seed("TENANT-002", 2L, "AGT-START", "ATIVA", LocalDate.now().plusDays(15), 250, "LIC-AGT-002"));
        save(seed("TENANT-003", 3L, "AGT-LITE", "EXPIRADA", LocalDate.now().minusDays(5), 50, "LIC-AGT-003"));
    }

    public synchronized void save(String tenantCodigo, LicencaEntity entity) {
        entity.setTenantCodigo(tenantCodigo);
        save(entity);
    }

    public synchronized LicencaEntity save(LicencaEntity entity) {
        if (entity.getId() == null) {
            entity.setId(sequence.getAndIncrement());
        }
        licencas.removeIf(existing -> existing.getTenantCodigo().equalsIgnoreCase(entity.getTenantCodigo()));
        licencas.add(entity);
        return entity;
    }

    public synchronized LicencaEntity findByTenantCodigo(String tenantCodigo) {
        if (tenantCodigo == null || tenantCodigo.isBlank()) {
            return null;
        }
        return licencas.stream()
                .filter(licenca -> licenca.getTenantCodigo().equalsIgnoreCase(tenantCodigo))
                .findFirst()
                .orElse(null);
    }

    public synchronized List<LicencaEntity> findAll() {
        return licencas.stream()
                .sorted(Comparator.comparing(LicencaEntity::getValidade))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    public synchronized long countAtivas() {
        return licencas.stream()
                .filter(licenca -> "ATIVA".equalsIgnoreCase(licenca.getEstado()))
                .filter(licenca -> !licenca.getValidade().isBefore(LocalDate.now()))
                .count();
    }

    public synchronized long countExpiringWithinDays(int days) {
        LocalDate limit = LocalDate.now().plusDays(days);
        return licencas.stream()
                .filter(licenca -> !licenca.getValidade().isBefore(LocalDate.now()))
                .filter(licenca -> !licenca.getValidade().isAfter(limit))
                .count();
    }

    private LicencaEntity seed(String tenantCodigo, Long tenantId, String plano, String estado,
                               LocalDate validade, int limiteFacturasDia, String chave) {
        LicencaEntity entity = new LicencaEntity();
        entity.setTenantCodigo(tenantCodigo);
        entity.setTenantId(tenantId);
        entity.setPlano(plano);
        entity.setEstado(estado);
        entity.setValidade(validade);
        entity.setLimiteFacturasDia(limiteFacturasDia);
        entity.setChave(chave);
        return entity;
    }
}
