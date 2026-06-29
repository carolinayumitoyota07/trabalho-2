package dao;

import jakarta.persistence.EntityManager;
import java.util.List;
import model.AnimalPreHistorico;
import util.JPAUtil;

public class AnimalDAO {

    public void salvar(AnimalPreHistorico animal) {
        EntityManager entityManager = JPAUtil.getEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(animal);
            entityManager.getTransaction().commit();
        }
        catch (RuntimeException exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }

            throw exception;
        }
        finally {
            entityManager.close();
        }
    }

    public List<AnimalPreHistorico> listarTodos() {
        EntityManager entityManager = JPAUtil.getEntityManager();

        try {
            return entityManager
                .createQuery("select animal from AnimalPreHistorico animal", AnimalPreHistorico.class)
                .getResultList();
        }
        finally {
            entityManager.close();
        }
    }

    public AnimalPreHistorico buscarPorId(Long id) {
        EntityManager entityManager = JPAUtil.getEntityManager();

        try {
            return entityManager.find(AnimalPreHistorico.class, id);
        }
        finally {
            entityManager.close();
        }
    }

    public void remover(Long id) {
        EntityManager entityManager = JPAUtil.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            AnimalPreHistorico animal = entityManager.find(AnimalPreHistorico.class, id);
            if (animal != null) {
                entityManager.remove(animal);
            }

            entityManager.getTransaction().commit();
        }
        catch (RuntimeException exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }

            throw exception;
        }
        finally {
            entityManager.close();
        }
    }
}
