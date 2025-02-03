package org.example;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    private String name;
    private BigDecimal quantity;

    public Product() {
    }

    public Product(String name, BigDecimal quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("Product(ID: %d, Name: %s, Quantity: %s)", productId, name, quantity);
    }
}
