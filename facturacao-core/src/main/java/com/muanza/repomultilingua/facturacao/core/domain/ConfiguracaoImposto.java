package com.muanza.repomultilingua.facturacao.core.domain;

import java.math.BigDecimal;

public class ConfiguracaoImposto {
    private Long id;
    private Long empresaId;
    private String tipoImposto;
    private BigDecimal percentual;

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

    public String getTipoImposto() {
        return tipoImposto;
    }

    public void setTipoImposto(String tipoImposto) {
        this.tipoImposto = tipoImposto;
    }

    public BigDecimal getPercentual() {
        return percentual;
    }

    public void setPercentual(BigDecimal percentual) {
        this.percentual = percentual;
    }
}

