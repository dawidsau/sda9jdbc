package pl.sda.jdbcjpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("sdajpaPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

    }
}
