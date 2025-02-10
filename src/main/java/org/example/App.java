package org.example;
import java.util.Arrays;
import java.util.List;


public class App {

    public static void main(String[] args) {
        JPA jpa = new JPA();

        Product product1 = new Product();
        product1.setName("Mac");
        product1.setPrice(1230.0);


        Product product2 = new Product();
        product2.setName("AirPods3");
        product2.setPrice(300.99);

        MyOrder order = new MyOrder();
        order.setOrderDate("2025-02-8");
        product1.setOrder(order);
        product2.setOrder(order);

        order.setProducts(Arrays.asList(product1,product2));
        jpa.addOrder(order);
        jpa.deleteOrder(9l);

    }
}