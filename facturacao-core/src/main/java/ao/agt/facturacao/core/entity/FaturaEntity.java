package ao.agt.facturacao.core.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FaturaEntity {
    public Long id;
    public Long empresaId;
    public String numeroSerie;
    public String numeroFatura;
    public LocalDateTime dataEmissao;
    public BigDecimal total;
    public String idioma;
    public String qrCode;
    public String assinaturaDigital;
}
