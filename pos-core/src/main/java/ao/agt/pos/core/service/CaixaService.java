package ao.agt.pos.core.service;

import ao.agt.pos.core.entity.CaixaEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CaixaService {
    public CaixaEntity abrir(Long terminalId, Long operadorId, BigDecimal saldoInicial) {
        CaixaEntity caixa = new CaixaEntity();
        caixa.terminalId = terminalId;
        caixa.operadorId = operadorId;
        caixa.saldoInicial = saldoInicial;
        caixa.dataAbertura = LocalDateTime.now();
        return caixa;
    }
}
