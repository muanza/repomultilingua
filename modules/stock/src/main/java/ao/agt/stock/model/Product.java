package ao.agt.stock.model;

import ao.agt.common.model.Entity;
import ao.agt.common.util.CurrencyUtils;
import java.math.BigDecimal;

public class Product extends Entity {

    private final String sku;
    private String name;
    private BigDecimal price;

    public Product(String sku, String name, BigDecimal price) {
        this.sku = sku;
        this.name = name;
        this.price = CurrencyUtils.normalize(price);
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
