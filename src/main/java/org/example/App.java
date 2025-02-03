package org.example;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.query.Query;


public class App {
    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Author.class)
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(Publisher.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            Author author1 = new Author();
            author1.setName("Kristijan Novak");

            Author author2 = new Author();
            author2.setName("Khaleid Hosseini");

            Publisher publisher1 = new Publisher();
            publisher1.setName("Znanje");

            Publisher publisher2 = new Publisher();
            publisher2.setName("Mozaik");

            Book book1 = new Book();
            book1.setTitle("Slučaj vlastite pogibelji");
            book1.setAuthor(author1);

            Book book2 = new Book();
            book2.setTitle("Gonič zmajeva");
            book2.setAuthor(author2);

            Set<Book> books1 = new HashSet<>();
            books1.add(book1);
            author1.setBooks(books1);

            Set<Book> books2 = new HashSet<>();
            books2.add(book2);
            author2.setBooks(books2);

            Set<Author> authors1 = new HashSet<>();
            authors1.add(author1);
            publisher1.setAuthors(authors1);

            Set<Author> authors2 = new HashSet<>();
            authors2.add(author2);
            publisher2.setAuthors(authors2);

            session.beginTransaction();

            session.save(author1);
            session.save(author2);
            session.save(book1);
            session.save(book2);
            session.save(publisher1);
            session.save(publisher2);

            session.getTransaction().commit();

            fetchAllAuthorsAndBooks(session);

            updateBookTitles(session, 1, "Novi naslov knjige");

            deleteBook(session, 1);

        } finally {
            factory.close();
        }
    }

    public static void deleteBook(Session session, long bookId) {
        String hql = "DELETE FROM Book WHERE id = :bookId";
        Query query = session.createQuery(hql);
        query.setParameter("bookId", bookId);

        int result = query.executeUpdate();
        System.out.println("Rows affected: " + result);
    }




    public static void updateBookTitles(Session session, long bookId, String newTitle) {
        String hql = "UPDATE Book b SET b.title = :newTitle WHERE b.id = :bookId";
        Query query = session.createQuery(hql);
        query.setParameter("newTitle", newTitle);
        query.setParameter("bookId", bookId);

        int result = query.executeUpdate();
        System.out.println("Rows affected: " + result);
    }

    public static void fetchAllAuthorsAndBooks(Session session) {
        String hql = "FROM Author a LEFT JOIN FETCH a.books";
        Query<Author> query = session.createQuery(hql, Author.class);
        query.setFetchSize(50);
        List<Author> authors = query.list();

        for (Author author : authors) {
            System.out.println("Author: " + author.getName());
            for (Book book : author.getBooks()) {
                System.out.println("Book Title: " + book.getTitle());
            }
        }
    }


}