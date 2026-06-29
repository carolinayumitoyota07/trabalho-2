package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class JPAUtil {

    private static final String PERSISTENCE_UNIT_NAME = "jurassicPU";
    private static final EntityManagerFactory entityManagerFactory =
        Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    private JPAUtil() {
    }

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public static void close() {
        if (entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
