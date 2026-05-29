package com.agt.crm.web.bean;

import com.agt.crm.service.LicencaService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named
@RequestScoped
public class CrmDashboardBean {
    @Inject
    LicencaService licencaService;

    public String getTitulo() {
        return "CRM Licenciamento Online";
    }

    public long getTotalLicencas() {
        return number("totalLicencas");
    }

    public long getLicencasValidas() {
        return number("licencasValidas");
    }

    public long getLicencasAExpirar() {
        return number("aExpirarEm30Dias");
    }

    public List<String> getAlertas() {
        return List.of(
                "2 tenants aguardam revisão comercial.",
                "Reforçar acompanhamento das licenças com expiração em menos de 30 dias."
        );
    }

    private long number(String key) {
        Map<String, Object> resumo = licencaService.resumo();
        Object value = resumo.get(key);
        return value instanceof Number ? ((Number) value).longValue() : 0L;
    }
}
