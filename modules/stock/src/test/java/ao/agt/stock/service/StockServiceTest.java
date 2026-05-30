package ao.agt.stock.service;

import ao.agt.stock.model.Product;
import ao.agt.stock.model.Stock;
import ao.agt.stock.model.StockMovement;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StockServiceTest {

    @Test
    void shouldApplyInAndOutMovements() {
        ProductService productService = new ProductService();
        StockService stockService = new StockService();

        Product product = productService.create("SKU-1", "Produto", java.math.BigDecimal.TEN);
        Stock stock = new Stock(product, 10);

        stockService.applyMovement(stock, new StockMovement("SKU-1", StockMovement.Type.IN, 5));
        stockService.applyMovement(stock, new StockMovement("SKU-1", StockMovement.Type.OUT, 3));

        assertEquals(12, stock.getQuantity());
    }
}
