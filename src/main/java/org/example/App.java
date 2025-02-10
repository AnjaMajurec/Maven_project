package org.example;
import java.util.Arrays;

public class App {

    public static void main(String[] args) {
        HibernateOrder hibernate = new HibernateOrder();

        Product product1 = new Product();
        product1.setName("Apple Watch2");
        product1.setPrice(350.0);


        Product product2 = new Product();
        product2.setName("iWallet");
        product2.setPrice(100.0);

        MyOrder order = new MyOrder();
        order.setOrderDate("2025-02-10");
        product1.setOrder(order);
        product2.setOrder(order);

        order.setProducts(Arrays.asList(product1,product2));
        hibernate.addOrder(order);
        hibernate.deleteOrder(12l);

    }
}