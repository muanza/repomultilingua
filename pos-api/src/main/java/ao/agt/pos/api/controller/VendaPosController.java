package ao.agt.pos.api.controller;

import ao.agt.pos.api.dto.VendaPosDTO;
import ao.agt.pos.core.entity.VendaPosEntity;
import ao.agt.pos.core.service.VendaPosService;

public class VendaPosController {
    private final VendaPosService service = new VendaPosService();

    public VendaPosDTO criar(VendaPosDTO dto) {
        VendaPosEntity entity = new VendaPosEntity();
        entity.terminalId = dto.terminalId;
        entity.operadorId = dto.operadorId;
        entity.valorTotal = java.math.BigDecimal.valueOf(dto.valorTotal);
        entity.meioPagamento = dto.meioPagamento;
        service.registrar(entity);
        return dto;
    }
}
