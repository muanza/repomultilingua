package com.agt.facturacao.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FacturaDTO {
    private String tenantCodigo;
    private Long clienteId;
    private String clienteNome;
    private String numero;
    private LocalDate dataEmissao;
    private String idioma;
    private String estado;
    private BigDecimal subtotal;
    private BigDecimal totalImposto;
    private BigDecimal total;

    public String getTenantCodigo() { return tenantCodigo; }
    public void setTenantCodigo(String tenantCodigo) { this.tenantCodigo = tenantCodigo; }
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public String getClienteNome() { return clienteNome; }
    public void setClienteNome(String clienteNome) { this.clienteNome = clienteNome; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public LocalDate getDataEmissao() { return dataEmissao; }
    public void setDataEmissao(LocalDate dataEmissao) { this.dataEmissao = dataEmissao; }
    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
    public BigDecimal getTotalImposto() { return totalImposto; }
    public void setTotalImposto(BigDecimal totalImposto) { this.totalImposto = totalImposto; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
}
