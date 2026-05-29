package com.agt.facturacao.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "factura")
public class FacturaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tenant_id", nullable = false)
    private Long tenantId;

    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    @Column(name = "cliente_nome", nullable = false)
    private String clienteNome;

    @Column(nullable = false)
    private String numero;

    @Column(name = "data_emissao", nullable = false)
    private LocalDate dataEmissao;

    @Column(nullable = false)
    private String idioma;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private BigDecimal subtotal;

    @Column(name = "total_imposto", nullable = false)
    private BigDecimal totalImposto;

    @Column(nullable = false)
    private BigDecimal total;

    public void setId(Long id) { this.id = id; }
    public Long getId() { return id; }
    public Long getTenantId() { return tenantId; }
    public void setTenantId(Long tenantId) { this.tenantId = tenantId; }
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
