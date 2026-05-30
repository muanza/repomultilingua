package ao.agt.stock.model;

public class Stock {

    private final Product product;
    private int quantity;

    public Stock(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public void increase(int value) {
        this.quantity += value;
    }

    public void decrease(int value) {
        this.quantity -= value;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
