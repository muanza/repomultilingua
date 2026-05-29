package com.agt.facturacao.entity;

import javax.persistence.*;

@Entity
@Table(name = "tenant")
public class TenantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(nullable = false)
    private String nome;

    @Column(name = "idioma_padrao", nullable = false)
    private String idiomaPadrao;

    public Long getId() { return id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getIdiomaPadrao() { return idiomaPadrao; }
    public void setIdiomaPadrao(String idiomaPadrao) { this.idiomaPadrao = idiomaPadrao; }
}
