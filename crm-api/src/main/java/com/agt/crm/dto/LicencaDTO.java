package com.agt.crm.dto;

import java.time.LocalDate;

public class LicencaDTO {
    private String tenantCodigo;
    private String estado;
    private LocalDate validade;

    public String getTenantCodigo() { return tenantCodigo; }
    public void setTenantCodigo(String tenantCodigo) { this.tenantCodigo = tenantCodigo; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public LocalDate getValidade() { return validade; }
    public void setValidade(LocalDate validade) { this.validade = validade; }
}
