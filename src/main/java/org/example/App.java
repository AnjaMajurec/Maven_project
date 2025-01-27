package org.example;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main( String[] args ) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("JpaExampleUnit");
        EntityManager em=emf.createEntityManager();

        try{
            em.getTransaction().begin();

            Subject subject1 = new Subject();
            subject1.setName("Physics");
            subject1.setDescription("Subject for 7th grade");
            subject1.setProfessor("prof. Domokuš");

            Subject subject2 = new Subject();
            subject2.setName("Mathematics");
            subject2.setDescription("Subject for 8th grade");
            subject2.setProfessor("prof. Viljevac");

            Student student1 = new Student();
            student1.setName("Anja");
            student1.setSurname("Majurec");

            Student student2 = new Student();
            student2.setName("Iva");
            student2.setSurname("Foret");

            student1.setSubjects(Arrays.asList(subject1,subject2));
            student2.setSubjects((Arrays.asList(subject2)));

            Grade student1grade = new Grade();
            student1grade.setValue(5);
            student1grade.setDate(LocalDate.of(2025,1,1));
            student1grade.setStudent(student1);
            student1grade.setSubject(subject2);

            Grade student2grade = new Grade();
            student2grade.setValue(4);
            student2grade.setDate(LocalDate.of(2025,1,5));
            student2grade.setStudent(student2);
            student2grade.setSubject(subject2);

            em.persist(subject1);
            em.persist(subject2);
            em.persist(student1);
            em.persist(student2);
            em.persist(student1grade);
            em.persist(student2grade);

            em.getTransaction().commit();

            String query1 = "SELECT s FROM Student s JOIN s.subjects sub WHERE sub.name = :subjectName";
            List<Student> mathsStudents = em.createQuery(query1, Student.class)
                    .setParameter("subjectName", "Mathematics")
                    .getResultList();


            System.out.println("Učenici koji slušaju predmet matematiku: ");
            for (Student student : mathsStudents) {
                System.out.println(student.getName() + " " + student.getSurname());
            }

            String query2 = "SELECT g FROM Grade g WHERE g.student.id = :studentId AND g.subject.name = :subjectName";
            List<Grade> gradesForStudentAndSubject = em.createQuery(query2, Grade.class)
                    .setParameter("studentId", student1.getId())
                    .setParameter("subjectName", "Mathematics")
                    .getResultList();

            System.out.println("Ocjene za učenika:"+student1.getName()+" "+student1.getSurname()+" za predmet Matematika: ");
            for (Grade grade : gradesForStudentAndSubject) {
                System.out.println("Ocjena: " + grade.getValue() + ", Datum: " + grade.getDate());
            }

            String query3 = "SELECT AVG(g.value) FROM Grade g WHERE g.subject.name = :subjectName";
            Double averageGradeForSubject = em.createQuery(query3, Double.class)
                    .setParameter("subjectName", "Mathematics")
                    .getSingleResult();

            System.out.println("Prosječna ocjena za predmet Matematika: " + averageGradeForSubject);




        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}




