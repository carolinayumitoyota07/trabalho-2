package app;

import jakarta.persistence.EntityManager;
import java.util.List;
import model.AnimalAereoPreHistorico;
import model.AnimalAquaticoPreHistorico;
import model.AnimalPreHistorico;
import model.AnimalTerrestrePreHistorico;
import model.Dieta;
import model.Porte;
import util.JPAUtil;

public class TestePersistenciaAnimal {

    public static void main(String[] args) {
        EntityManager entityManager = null;

        try {
            entityManager = JPAUtil.getEntityManager();

            entityManager.getTransaction().begin();

            String sufixo = String.valueOf(System.currentTimeMillis());
            AnimalPreHistorico animalTerrestre = new AnimalTerrestrePreHistorico(
                "AT-" + sufixo,
                "Tiranossauro Rex",
                "Tyrannosaurus rex",
                Dieta.CARNIVORO,
                Porte.GRANDE,
                10
            );
            AnimalPreHistorico animalAquatico = new AnimalAquaticoPreHistorico(
                "AA-" + sufixo,
                "Mosassauro",
                "Mosasaurus hoffmannii",
                Dieta.CARNIVORO,
                Porte.GRANDE,
                9
            );
            AnimalPreHistorico animalAereo = new AnimalAereoPreHistorico(
                "AE-" + sufixo,
                "Pteranodonte",
                "Pteranodon longiceps",
                Dieta.CARNIVORO,
                Porte.MEDIO,
                7
            );

            entityManager.persist(animalTerrestre);
            entityManager.persist(animalAquatico);
            entityManager.persist(animalAereo);

            entityManager.getTransaction().commit();
            System.out.println("Animais persistidos com sucesso.");

            List<AnimalPreHistorico> animais = entityManager
                .createQuery("select animal from AnimalPreHistorico animal", AnimalPreHistorico.class)
                .getResultList();

            System.out.println("Animais recuperados do banco:");
            for (AnimalPreHistorico animal : animais) {
                animal.exibirDados();
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
