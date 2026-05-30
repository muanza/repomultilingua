package com.muanza.repomultilingua.facturacao.core.domain;

import java.math.BigDecimal;

public class Produto {
    private Long id;
    private Long empresaId;
    private String codigo;
    private String descricao;
    private BigDecimal precoVenda;
    private BigDecimal percentualImposto;
    private boolean ativo = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    public BigDecimal getPercentualImposto() {
        return percentualImposto;
    }

    public void setPercentualImposto(BigDecimal percentualImposto) {
        this.percentualImposto = percentualImposto;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}

