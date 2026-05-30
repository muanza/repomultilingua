package ao.agt.facturacao.model;

import ao.agt.common.util.CurrencyUtils;
import java.math.BigDecimal;

public class InvoiceLine {

    private final String description;
    private final int quantity;
    private final BigDecimal unitPrice;

    public InvoiceLine(String description, int quantity, BigDecimal unitPrice) {
        this.description = description;
        this.quantity = quantity;
        this.unitPrice = CurrencyUtils.normalize(unitPrice);
    }

    public BigDecimal getLineTotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
}
