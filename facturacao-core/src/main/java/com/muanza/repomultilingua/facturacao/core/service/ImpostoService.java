package com.muanza.repomultilingua.facturacao.core.service;

import java.math.BigDecimal;

public class ImpostoService {
    public BigDecimal calcular(BigDecimal base, BigDecimal percentual) {
        return base.multiply(percentual).divide(BigDecimal.valueOf(100));
    }
}

