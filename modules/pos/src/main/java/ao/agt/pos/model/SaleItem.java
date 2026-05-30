package ao.agt.pos.model;

import ao.agt.common.util.CurrencyUtils;
import java.math.BigDecimal;

public class SaleItem {

    private final String sku;
    private final int quantity;
    private final BigDecimal unitPrice;

    public SaleItem(String sku, int quantity, BigDecimal unitPrice) {
        this.sku = sku;
        this.quantity = quantity;
        this.unitPrice = CurrencyUtils.normalize(unitPrice);
    }

    public BigDecimal getTotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public String getSku() {
        return sku;
    }
}
