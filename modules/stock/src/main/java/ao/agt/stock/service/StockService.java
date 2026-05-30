package ao.agt.stock.service;

import ao.agt.common.exception.BusinessException;
import ao.agt.stock.model.Stock;
import ao.agt.stock.model.StockMovement;

public class StockService {

    public void applyMovement(Stock stock, StockMovement movement) {
        if (movement.getQuantity() <= 0) {
            throw new BusinessException("Stock movement quantity must be positive");
        }
        if (movement.getType() == StockMovement.Type.IN) {
            stock.increase(movement.getQuantity());
            return;
        }
        if (stock.getQuantity() < movement.getQuantity()) {
            throw new BusinessException("Insufficient stock");
        }
        stock.decrease(movement.getQuantity());
    }
}
