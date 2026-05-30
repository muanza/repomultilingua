package ao.agt.pos.service;

import ao.agt.common.exception.BusinessException;
import ao.agt.pos.model.Sale;
import ao.agt.pos.model.SaleItem;

public class SaleService {

    public Sale startSale() {
        return new Sale();
    }

    public void addItem(Sale sale, SaleItem item) {
        if (item == null) {
            throw new BusinessException("Sale item is required");
        }
        sale.addItem(item);
    }
}
