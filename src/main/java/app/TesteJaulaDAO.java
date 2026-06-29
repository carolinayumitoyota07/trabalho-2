package app;

import dao.AnimalDAO;
import dao.JaulaDAO;
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

public class TesteJaulaDAO {

    public static void main(String[] args) {
        try {
            AnimalDAO animalDAO = new AnimalDAO();
            JaulaDAO jaulaDAO = new JaulaDAO();
            String sufixo = String.valueOf(System.currentTimeMillis());

            AnimalPreHistorico animalTerrestre = new AnimalTerrestrePreHistorico(
                "DAO-JAULA-AT-" + sufixo,
                "Tiranossauro Rex",
                "Tyrannosaurus rex",
                Dieta.CARNIVORO,
                Porte.GRANDE,
                10
            );
            AnimalPreHistorico animalAquatico = new AnimalAquaticoPreHistorico(
                "DAO-JAULA-AA-" + sufixo,
                "Mosassauro",
                "Mosasaurus hoffmannii",
                Dieta.CARNIVORO,
                Porte.GRANDE,
                9
            );

            animalDAO.salvar(animalTerrestre);
            animalDAO.salvar(animalAquatico);
            System.out.println("Animais salvos pelo AnimalDAO.");

            Jaula jaulaTerrestre = new JaulaTerrestre(
                "DAO-JT-" + sufixo,
                2,
                NivelSeguranca.ALTO,
                500.0
            );
            Jaula jaulaAquatica = new JaulaAquatica(
                "DAO-JA-" + sufixo,
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

            jaulaDAO.salvar(jaulaTerrestre);
            jaulaDAO.salvar(jaulaAquatica);
            System.out.println("Jaulas salvas pelo JaulaDAO.");

            jaulaAquatica.adicionarAnimal(animalAquatico);
            jaulaAquatica = jaulaDAO.atualizar(jaulaAquatica);
            System.out.println("Jaula aquatica atualizada com animal compativel.");

            System.out.println();
            System.out.println("Listagem de jaulas:");
            List<Jaula> jaulas = jaulaDAO.listarTodas();
            for (Jaula jaula : jaulas) {
                jaula.exibirDados();
                System.out.println();
            }

            Long idBusca = jaulaTerrestre.getId();
            Jaula jaulaEncontrada = jaulaDAO.buscarPorId(idBusca);
            System.out.println("Busca por ID " + idBusca + ":");
            if (jaulaEncontrada != null) {
                jaulaEncontrada.exibirDados();
            }
            else {
                System.out.println("Jaula nao encontrada.");
            }

            System.out.println();
            Long idRemocao = jaulaAquatica.getId();
            jaulaDAO.remover(idRemocao);
            System.out.println("Jaula removida pelo ID " + idRemocao + ".");

            System.out.println();
            System.out.println("Listagem apos remocao:");
            List<Jaula> jaulasAposRemocao = jaulaDAO.listarTodas();
            for (Jaula jaula : jaulasAposRemocao) {
                jaula.exibirDados();
                System.out.println();
            }
        }
        finally {
            JPAUtil.close();
        }
    }
}
