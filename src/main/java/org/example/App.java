package org.example;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class App 
{
    public static void main( String[] args ) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("JpaExampleUnit");
        EntityManager em=emf.createEntityManager();

                try {
                    em.getTransaction().begin();


                    Company company = new Company();
                    company.setName("Podravka");

                    Person person1 = new Person();
                    person1.setName("Anja Majurec");

                    Person person2 = new Person();
                    person2.setName("Ivor Stojevski");

                    Contract contract1 = new Contract();
                    contract1.setStartDate(LocalDate.of(2025, 1, 2));
                    contract1.setDurationInMonths(12);
                    contract1.setSalary(new BigDecimal("2500"));
                    contract1.setCompany(company);

                    Contract contract2 = new Contract();
                    contract2.setStartDate(LocalDate.of(2023, 3, 1));
                    contract2.setDurationInMonths(24);
                    contract2.setSalary(new BigDecimal("6000"));
                    contract2.setCompany(company);

                    Set<Person> personsForContract1 = new HashSet<>();
                    personsForContract1.add(person1);
                    personsForContract1.add(person2);

                    Set<Person> personsForContract2 = new HashSet<>();
                    personsForContract2.add(person1);

                    contract1.setPersons(personsForContract1);
                    contract2.setPersons(personsForContract2);


                    em.persist(company);
                    em.persist(person1);
                    em.persist(person2);
                    em.persist(contract1);
                    em.persist(contract2);


                    contract1.setSalary(new BigDecimal("3000"));
                    em.merge(contract1);

                    em.remove(contract2);

                    em.getTransaction().commit();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }




