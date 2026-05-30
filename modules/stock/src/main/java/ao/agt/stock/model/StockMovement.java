package ao.agt.stock.model;

import java.time.OffsetDateTime;

public class StockMovement {

    public enum Type { IN, OUT }

    private final String sku;
    private final Type type;
    private final int quantity;
    private final OffsetDateTime movedAt;

    public StockMovement(String sku, Type type, int quantity) {
        this.sku = sku;
        this.type = type;
        this.quantity = quantity;
        this.movedAt = OffsetDateTime.now();
    }

    public String getSku() {
        return sku;
    }

    public Type getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public OffsetDateTime getMovedAt() {
        return movedAt;
    }
}
