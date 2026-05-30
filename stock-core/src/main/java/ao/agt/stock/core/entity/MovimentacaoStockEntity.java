package ao.agt.stock.core.entity;

import java.time.LocalDateTime;

public class MovimentacaoStockEntity {
    public Long id;
    public Long produtoId;
    public Long armazemId;
    public String tipoMovimento;
    public double quantidade;
    public LocalDateTime dataMovimento;
}
