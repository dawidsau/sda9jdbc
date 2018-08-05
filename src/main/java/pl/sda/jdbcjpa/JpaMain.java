package pl.sda.jdbcjpa;

import com.google.common.collect.Lists;
import pl.sda.jdbcjpa.order.Order;
import pl.sda.jdbcjpa.order.OrderStatus;
import pl.sda.jdbcjpa.person.Customer;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class JpaMain {

    public final static EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("sdajpaPU");

    public static void main(String[] args) {
//        createCustomer();
//        findCustomerJPQL("Nowak");
//        findCustomerEM(1);

        createCustomerWithOrder();
    }

    private static void createCustomerWithOrder() {
        Customer customer = new Customer();
        customer.setFirstName("Karol");
        customer.setSurname("Kowalski");
        customer.setStreet("Jagodowa");
        customer.setCity("Jelenia Góra");
        customer.setPostalCode("88-888");
        customer.setThisFieldIsNotToPersist("BUM");

        Order order = new Order();
        order.setCreateDate(new Date());
        order.setOrderStatus(OrderStatus.NEW);
        order.setTotalCost(BigDecimal.valueOf(1.9));
        order.setCustomerName("Karol K");

        customer.setOrdersList(Lists.newArrayList(order));
        order.setCustomer(customer);

        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        entityManager.getTransaction().begin();
//        entityManager.persist(customer);
        entityManager.persist(order);
        entityManager.getTransaction().commit();
        List<Order> ordersList = customer.getOrdersList();
        System.out.println();
    }

    private static void findCustomerEM(Integer id) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        Customer customer = entityManager.find(Customer.class, id);
        System.out.println(customer);
    }

    private static void findCustomerJPQL(String surname) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
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
