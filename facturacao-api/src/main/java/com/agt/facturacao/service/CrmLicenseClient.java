package com.agt.facturacao.service;

import com.agt.common.exception.BusinessException;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@ApplicationScoped
public class CrmLicenseClient {
    private final Map<String, TenantLicenseProfile> tenantProfiles = new LinkedHashMap<>();

    public CrmLicenseClient() {
        register(new TenantLicenseProfile(1L, "TENANT-001", "Tenant Demonstração", "pt", "AGT-PRO", 1000,
                LocalDate.now().plusDays(365), true));
        register(new TenantLicenseProfile(2L, "TENANT-002", "Tenant Expiração", "en", "AGT-START", 250,
                LocalDate.now().plusDays(15), true));
        register(new TenantLicenseProfile(3L, "TENANT-003", "Tenant Suspenso", "fr", "AGT-LITE", 50,
                LocalDate.now().minusDays(1), false));
    }

    public boolean licencaActiva(String tenantCodigo) {
        if (tenantCodigo == null || tenantCodigo.isBlank()) {
            return false;
        }
        TenantLicenseProfile profile = tenantProfiles.get(tenantCodigo.trim().toUpperCase());
        return profile != null && profile.isLicencaActiva();
    }

    public TenantLicenseProfile requireTenantProfile(String tenantCodigo) {
        if (tenantCodigo == null || tenantCodigo.isBlank()) {
            throw new BusinessException("Tenant é obrigatório");
        }
        TenantLicenseProfile profile = tenantProfiles.get(tenantCodigo.trim().toUpperCase());
        if (profile == null) {
            throw new BusinessException("Tenant não registado no CRM");
        }
        if (!profile.isLicencaActiva()) {
            throw new BusinessException("Licença do tenant inactiva ou expirada");
        }
        return profile;
    }

    public Collection<TenantLicenseProfile> listTenantProfiles() {
        return tenantProfiles.values();
    }

    private void register(TenantLicenseProfile profile) {
        tenantProfiles.put(profile.getTenantCodigo(), profile);
    }

    public static final class TenantLicenseProfile {
        private final Long tenantId;
        private final String tenantCodigo;
        private final String tenantNome;
        private final String idiomaPadrao;
        private final String plano;
        private final int limiteFacturasDia;
        private final LocalDate validade;
        private final boolean activa;

        private TenantLicenseProfile(Long tenantId, String tenantCodigo, String tenantNome, String idiomaPadrao,
                                     String plano, int limiteFacturasDia, LocalDate validade, boolean activa) {
            this.tenantId = tenantId;
            this.tenantCodigo = tenantCodigo;
            this.tenantNome = tenantNome;
            this.idiomaPadrao = idiomaPadrao;
            this.plano = plano;
            this.limiteFacturasDia = limiteFacturasDia;
            this.validade = validade;
            this.activa = activa;
        }

        public Long getTenantId() { return tenantId; }
        public String getTenantCodigo() { return tenantCodigo; }
        public String getTenantNome() { return tenantNome; }
        public String getIdiomaPadrao() { return idiomaPadrao; }
        public String getPlano() { return plano; }
        public int getLimiteFacturasDia() { return limiteFacturasDia; }
        public LocalDate getValidade() { return validade; }

        public boolean isLicencaActiva() {
            return activa && !validade.isBefore(LocalDate.now());
        }
    }
}
