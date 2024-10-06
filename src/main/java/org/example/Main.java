package org.example;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.example.model.Person;
public class Main {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Person.class)
                .buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();

            session.beginTransaction();
            Person person1 = new Person("Mustafa", 67);
            session.save(person1);
            Person person2 = new Person("Farrukh", 12);
            session.save(person2);
            System.out.println("Person saved successfully");

            Person rPerson = session.get(Person.class, person2.getId());
            System.out.println("Person retrieved successfully");
            System.out.println(rPerson);

            rPerson.setName("Aladdin");
            rPerson.setAge(33);
            session.update(rPerson);
            System.out.println("Person updated successfully");

            session.delete(rPerson);
            System.out.println("Person deleted successfully");


            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}