package org.example;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);



        while (true) {
            System.out.println("IZBORNIK: 1 - Unesi novog polaznika");
            System.out.println("2 - Unos novog programa obrazovanja");
            System.out.println("3 - Upiši polaznika na program obrazovanja");
            System.out.println("4 - Prebaci polaznika iz jednog u drugi program obrazovanja");
            System.out.println("5 - Ispis polaznika određenog programa obradovanja (po ID-ju)");
            System.out.println("6 - Kraj");
            System.out.println("Molim Vas izaberite jednu od ponuđenih opcija: ");

            int unos = scanner.nextInt();
            scanner.nextLine();

            switch (unos) {
                case 1:
                    unosNovogPolaznika(scanner);
                    break;

                case 2:
                    System.out.println("Izašli ste iz aplikacije.");
                    return;

                default:
                    System.out.println("Krivi unos. Probajte ponovno.");
            }
        }
    }
    public static void unosNovogPolaznika(Scanner scanner) {
        System.out.println("Unesite ime polaznika: ");
        String ime = scanner.nextLine();
        System.out.println("Unesite prezime polaznika: ");
        String prezime = scanner.nextLine();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Polaznik polaznik = new Polaznik(ime, prezime);
            session.save(polaznik);
            transaction.commit();
            System.out.println("Polaznik " + ime + " " + prezime + " je uspješno unesen u bazu.");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}