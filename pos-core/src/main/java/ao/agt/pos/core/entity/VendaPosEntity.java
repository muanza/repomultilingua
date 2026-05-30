package ao.agt.pos.core.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VendaPosEntity {
    public Long id;
    public Long empresaId;
    public Long operadorId;
    public Long terminalId;
    public LocalDateTime dataVenda;
    public BigDecimal valorTotal;
    public String meioPagamento;
}
