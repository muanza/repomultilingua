package ao.agt.pos.model;

import ao.agt.common.model.Entity;
import ao.agt.common.util.CurrencyUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sale extends Entity {

    private final List<SaleItem> items = new ArrayList<>();

    public void addItem(SaleItem item) {
        items.add(item);
    }

    public BigDecimal getTotal() {
        return CurrencyUtils.normalize(items.stream().map(SaleItem::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add));
    }

    public List<SaleItem> getItems() {
        return Collections.unmodifiableList(items);
    }
}
