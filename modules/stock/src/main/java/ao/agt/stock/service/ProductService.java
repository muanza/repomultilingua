package ao.agt.stock.service;

import ao.agt.stock.model.Product;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ProductService {

    private final Map<String, Product> products = new ConcurrentHashMap<>();

    public Product create(String sku, String name, BigDecimal price) {
        Product product = new Product(sku, name, price);
        products.put(sku, product);
        return product;
    }

    public Optional<Product> findBySku(String sku) {
        return Optional.ofNullable(products.get(sku));
    }
}
