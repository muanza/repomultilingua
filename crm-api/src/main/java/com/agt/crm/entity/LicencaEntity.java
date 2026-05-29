package com.agt.crm.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "licenca")
public class LicencaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tenant_id", nullable = false)
    private Long tenantId;

    @Column(nullable = false)
    private String plano;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private LocalDate validade;

    public Long getId() { return id; }
    public Long getTenantId() { return tenantId; }
    public void setTenantId(Long tenantId) { this.tenantId = tenantId; }
    public String getPlano() { return plano; }
    public void setPlano(String plano) { this.plano = plano; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public LocalDate getValidade() { return validade; }
    public void setValidade(LocalDate validade) { this.validade = validade; }
}
