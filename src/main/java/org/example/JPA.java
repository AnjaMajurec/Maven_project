package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class JPA {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JpaExampleUnit");

    public void addOrder(MyOrder order) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();
        em.close();
    }

    public List<MyOrder> getAllOrders() {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT o FROM MyOrder o";
        TypedQuery<MyOrder> query = em.createQuery(jpql, MyOrder.class);
        List<MyOrder> orders = query.getResultList();
        em.close();
        return orders;
    }

    public MyOrder getOrderById(Long id) {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT o FROM MyOrder o WHERE o.id = :id";
        TypedQuery<MyOrder> query = em.createQuery(jpql, MyOrder.class);
        query.setParameter("id", id);
        MyOrder order = query.getSingleResult();
        em.close();
        return order;
    }

    public void updateOrder(Long orderId, List<Product> updatedProducts) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        MyOrder order = em.find(MyOrder.class, orderId);
        if (order != null) {
            order.setProducts(updatedProducts);
            em.merge(order);
        }
        em.getTransaction().commit();
        em.close();
    }

    public void deleteOrder(Long orderId) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        MyOrder order = em.find(MyOrder.class, orderId);
        if (order != null) {
            em.remove(order);
        }
        em.getTransaction().commit();
        em.close();
    }
}
