package app;

import jakarta.persistence.EntityManager;
import util.JPAUtil;

public class TesteConexaoJPA {

    public static void main(String[] args) {
        EntityManager entityManager = null;

        try {
            entityManager = JPAUtil.getEntityManager();
            System.out.println("Conexao JPA aberta com sucesso.");
        }
        finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }

            JPAUtil.close();
            System.out.println("Conexao JPA fechada com sucesso.");
        }
    }
}
