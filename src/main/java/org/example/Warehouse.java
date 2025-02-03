package org.example;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "warehouses")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int warehouseId;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @MapKey(name = "productId")
    @JoinColumn(name = "warehouse_id")
    private Map<Integer, Product> products = new HashMap<>();

    public Warehouse() {
    }

    public Warehouse(String name) {
        this.name = name;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public String getName() {
        return name;
    }

    public Map<Integer, Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
    }

    public Product findProduct(int productId) {
        return products.get(productId);
    }

    @Override
    public String toString() {
        return String.format("Warehouse(ID: %d, Name: %s)", warehouseId, name);
    }
}
