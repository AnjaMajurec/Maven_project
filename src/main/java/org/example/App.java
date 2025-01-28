package org.example;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;

public class App {

    private static SessionFactory sessionFactory;

    public static void main(String[] args) {

        sessionFactory = new Configuration()
                .setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect")
                .setProperty("hibernate.connection.driver_class", "com.microsoft.sqlserver.jdbc.SQLServerDriver")
                .setProperty("hibernate.connection.url", "jdbc:sqlserver://localhost:1433;databaseName=AdventureWorksOBP;encrypt=true;trustServerCertificate=true")
                .setProperty("hibernate.connection.username", "root")
                .setProperty("hibernate.connection.password", "password")
                .setProperty("hibernate.hbm2ddl.auto", "update")
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.format_sql", "true")
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();

        addProduct(new Product ("iPhone", BigDecimal.valueOf(1100)));
        addProduct(new Product ("AirPods", BigDecimal.valueOf(250)));
        updateProduct(1L,BigDecimal.valueOf(1400));
        selectProduct(1L);
        deleteProduct(1L);

        sessionFactory.close();
    }
    public static void addProduct(Product product){
        Session session=sessionFactory.openSession();
        Transaction transaction=null;

        try{
            transaction=session.beginTransaction();
            session.save(product);
            transaction.commit();
            System.out.println("Product added successfully!");
        }catch(Exception e){
            if(transaction!=null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }
    }

    public static void updateProduct(Long productId, BigDecimal newPrice){
        Session session=sessionFactory.openSession();
        Transaction transaction=null;
        try{
            transaction = session.beginTransaction();
            Product product=session.get(Product.class,productId);
            if(product!=null){
                product.setPrice(newPrice);
                session.update(product);
                transaction.commit();
                System.out.println("Product price updated successfully!");
            }
            else{
                System.out.println("Product not found. Try again!");
            }
        }catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public static void selectProduct(Long productId){
        Session session=sessionFactory.openSession();
        try{
            Product product=session.get(Product.class,productId);
            if(product!=null){
                System.out.println("Product: "+product.getName()+" with price "+product.getPrice()+".");
            }
            else{
                System.out.println("Product not found. Try again!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public static void deleteProduct(Long productId){
        Session session=sessionFactory.openSession();
        Transaction transaction=null;

        try{
            transaction=session.beginTransaction();
            Product product=session.get(Product.class,productId);
            if(product!=null){
                session.delete(product);
                transaction.commit();
                System.out.println("Product deleted successfully!");
            }else{
                System.out.println("Product not found. Try again!");
            }
        }catch(Exception e){
            if(transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}





