package ao.agt.pos.core.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CaixaEntity {
    public Long id;
    public Long terminalId;
    public Long operadorId;
    public LocalDateTime dataAbertura;
    public LocalDateTime dataEncerramento;
    public BigDecimal saldoInicial;
    public BigDecimal diferencaCaixa;
}
