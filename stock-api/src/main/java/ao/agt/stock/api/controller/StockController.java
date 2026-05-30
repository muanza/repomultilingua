package ao.agt.stock.api.controller;

import ao.agt.stock.api.dto.ProdutoStockDTO;
import java.util.ArrayList;
import java.util.List;

public class StockController {
    private final List<ProdutoStockDTO> itens = new ArrayList<>();

    public ProdutoStockDTO registrar(ProdutoStockDTO dto) {
        itens.add(dto);
        return dto;
    }

    public List<ProdutoStockDTO> listar() {
        return List.copyOf(itens);
    }
}
