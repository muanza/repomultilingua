package ao.agt.facturacao.api.controller;

import ao.agt.facturacao.api.dto.FaturaDTO;
import java.util.ArrayList;
import java.util.List;

public class FaturacaoController {
    private final List<FaturaDTO> faturas = new ArrayList<>();

    public FaturaDTO emitir(FaturaDTO dto) {
        faturas.add(dto);
        return dto;
    }

    public List<FaturaDTO> listar() {
        return List.copyOf(faturas);
    }
}
