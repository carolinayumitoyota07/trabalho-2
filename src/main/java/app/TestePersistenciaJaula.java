package app;

import jakarta.persistence.EntityManager;
import java.util.List;
import model.AnimalAquaticoPreHistorico;
import model.AnimalPreHistorico;
import model.AnimalTerrestrePreHistorico;
import model.Dieta;
import model.Jaula;
import model.JaulaAquatica;
import model.JaulaTerrestre;
import model.NivelSeguranca;
import model.Porte;
import util.JPAUtil;

public class TestePersistenciaJaula {

    public static void main(String[] args) {
        EntityManager entityManager = null;

        try {
            entityManager = JPAUtil.getEntityManager();

            entityManager.getTransaction().begin();

            String sufixo = String.valueOf(System.currentTimeMillis());
            AnimalPreHistorico animalTerrestre = new AnimalTerrestrePreHistorico(
                "JAULA-AT-" + sufixo,
                "Tiranossauro Rex",
                "Tyrannosaurus rex",
                Dieta.CARNIVORO,
                Porte.GRANDE,
                10
            );
            AnimalPreHistorico animalAquatico = new AnimalAquaticoPreHistorico(
                "JAULA-AA-" + sufixo,
                "Mosassauro",
                "Mosasaurus hoffmannii",
                Dieta.CARNIVORO,
                Porte.GRANDE,
                9
            );

            entityManager.persist(animalTerrestre);
            entityManager.persist(animalAquatico);

            Jaula jaulaTerrestre = new JaulaTerrestre(
                "JT-JPA-" + sufixo,
                2,
                NivelSeguranca.ALTO,
                500.0
            );
            Jaula jaulaAquatica = new JaulaAquatica(
                "JA-JPA-" + sufixo,
                2,
                NivelSeguranca.ALTO,
                800.0
            );

            System.out.println("Alocacao compativel:");
            jaulaTerrestre.adicionarAnimal(animalTerrestre);

            System.out.println("Alocacao incompativel:");
            jaulaTerrestre.adicionarAnimal(animalAquatico);
            System.out.println(
                "Animal aquatico na jaula terrestre? " +
                (jaulaTerrestre.getAnimaisAlocados().contains(animalAquatico) ? "Sim" : "Nao")
            );

            jaulaAquatica.adicionarAnimal(animalAquatico);

            entityManager.persist(jaulaTerrestre);
            entityManager.persist(jaulaAquatica);

            entityManager.getTransaction().commit();
            System.out.println("Jaulas persistidas com sucesso.");

            List<Jaula> jaulas = entityManager
                .createQuery("select jaula from Jaula jaula", Jaula.class)
                .getResultList();

            System.out.println();
            System.out.println("Jaulas recuperadas do banco:");
            for (Jaula jaula : jaulas) {
                jaula.exibirDados();
                System.out.println();
            }
        }
        catch (RuntimeException exception) {
            if (
                entityManager != null &&
                entityManager.getTransaction().isActive()
            ) {
                entityManager.getTransaction().rollback();
            }

            throw exception;
        }
        finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }

            JPAUtil.close();
        }
    }
}
