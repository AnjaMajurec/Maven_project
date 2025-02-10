package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateOrder {
    public void addOrder(MyOrder order) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
    public List<MyOrder> getAllOrders() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM MyOrder o JOIN FETCH o.products";
            Query<MyOrder> query = session.createQuery(hql, MyOrder.class);
            return query.list();
        }
    }
    public void updateOrder(Long orderId, List<Product> updatedProducts) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            MyOrder order = session.get(MyOrder.class, orderId);
            if (order != null) {
                order.setProducts(updatedProducts);
                session.update(order);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
    public void deleteOrder(Long orderId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            MyOrder order = session.get(MyOrder.class, orderId);
            if (order != null) {
                session.delete(order);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
