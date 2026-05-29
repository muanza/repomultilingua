package com.agt.facturacao.dto;

import java.math.BigDecimal;

public class FacturaDTO {
    private String numero;
    private String idioma;
    private BigDecimal total;

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
}
