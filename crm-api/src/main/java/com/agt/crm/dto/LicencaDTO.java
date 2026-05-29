package com.agt.crm.dto;

import java.time.LocalDate;

public class LicencaDTO {
    private String tenantCodigo;
    private String plano;
    private String estado;
    private LocalDate validade;
    private Integer limiteFacturasDia;
    private boolean valida;
    private String chaveMascarada;

    public String getTenantCodigo() { return tenantCodigo; }
    public void setTenantCodigo(String tenantCodigo) { this.tenantCodigo = tenantCodigo; }
    public String getPlano() { return plano; }
    public void setPlano(String plano) { this.plano = plano; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public LocalDate getValidade() { return validade; }
    public void setValidade(LocalDate validade) { this.validade = validade; }
    public Integer getLimiteFacturasDia() { return limiteFacturasDia; }
    public void setLimiteFacturasDia(Integer limiteFacturasDia) { this.limiteFacturasDia = limiteFacturasDia; }
    public boolean isValida() { return valida; }
    public void setValida(boolean valida) { this.valida = valida; }
    public String getChaveMascarada() { return chaveMascarada; }
    public void setChaveMascarada(String chaveMascarada) { this.chaveMascarada = chaveMascarada; }
}
