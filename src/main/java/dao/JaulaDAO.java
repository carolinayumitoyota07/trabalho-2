package dao;

import jakarta.persistence.EntityManager;
import java.util.List;
import model.Jaula;
import util.JPAUtil;

public class JaulaDAO {

    public void salvar(Jaula jaula) {
        EntityManager entityManager = JPAUtil.getEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(jaula);
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

    public List<Jaula> listarTodas() {
        EntityManager entityManager = JPAUtil.getEntityManager();

        try {
            return entityManager
                .createQuery(
                    "select distinct jaula from Jaula jaula left join fetch jaula.animaisAlocados",
                    Jaula.class
                )
                .getResultList();
        }
        finally {
            entityManager.close();
        }
    }

    public Jaula buscarPorId(Long id) {
        EntityManager entityManager = JPAUtil.getEntityManager();

        try {
            List<Jaula> jaulas = entityManager
                .createQuery(
                    "select jaula from Jaula jaula left join fetch jaula.animaisAlocados where jaula.id = :id",
                    Jaula.class
                )
                .setParameter("id", id)
                .getResultList();

            if (jaulas.isEmpty()) {
                return null;
            }

            return jaulas.get(0);
        }
        finally {
            entityManager.close();
        }
    }

    public Jaula atualizar(Jaula jaula) {
        EntityManager entityManager = JPAUtil.getEntityManager();

        try {
            entityManager.getTransaction().begin();
            Jaula jaulaAtualizada = entityManager.merge(jaula);
            entityManager.getTransaction().commit();
            return jaulaAtualizada;
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

    public void atualizarDuasJaulas(Jaula jaulaOrigem, Jaula jaulaDestino) {
        EntityManager entityManager = JPAUtil.getEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(jaulaOrigem);
            entityManager.merge(jaulaDestino);
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

    public void remover(Long id) {
        EntityManager entityManager = JPAUtil.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            Jaula jaula = entityManager.find(Jaula.class, id);
            if (jaula != null) {
                entityManager.remove(jaula);
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
