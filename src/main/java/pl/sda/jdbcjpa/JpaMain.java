package pl.sda.jdbcjpa;

import pl.sda.jdbcjpa.person.Customer;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    private final static EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("sdajpaPU");

    public static void main(String[] args) {
        createCustomer();
        findCustomer("Nowak");
    }

    private static void findCustomer(String surname) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Customer> query = entityManager.createQuery(
                "select c from Customer c where c.surname = :surname",
                Customer.class);
//        query.setParameter(0, surname); // można używać z "?"
        query.setParameter("surname", surname);
        List<Customer> resultList = query.getResultList();
        System.out.println(resultList);

    }

    private static void createCustomer() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Customer customer = new Customer();
        customer.setFirstName("Andrzej");
        customer.setSurname("Nowak");
        customer.setCity("Zielona Góra");
        customer.setPostalCode("65-001");
        customer.setStreet("Akacjowa");
        transaction.begin();
        entityManager.persist(customer);
        transaction.commit();
    }
}
