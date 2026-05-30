package ao.agt.pos.api.controller;

import ao.agt.pos.api.dto.CaixaDTO;
import ao.agt.pos.core.entity.CaixaEntity;
import ao.agt.pos.core.service.CaixaService;

public class CaixaController {
    private final CaixaService service = new CaixaService();

    public CaixaEntity abrir(CaixaDTO dto) {
        return service.abrir(dto.terminalId, dto.operadorId, dto.saldoInicial);
    }
}
