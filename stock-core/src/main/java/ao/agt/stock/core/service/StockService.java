package ao.agt.stock.core.service;

import ao.agt.stock.core.entity.ProdutoStockEntity;

public class StockService {
    public boolean estaAbaixoDoMinimo(ProdutoStockEntity produtoStock) {
        return produtoStock.quantidadeAtual <= produtoStock.quantidadeMinima;
    }
}
