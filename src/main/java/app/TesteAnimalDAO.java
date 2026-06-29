package app;

import dao.AnimalDAO;
import java.util.List;
import model.AnimalAereoPreHistorico;
import model.AnimalAquaticoPreHistorico;
import model.AnimalPreHistorico;
import model.AnimalTerrestrePreHistorico;
import model.Dieta;
import model.Porte;
import util.JPAUtil;

public class TesteAnimalDAO {

    public static void main(String[] args) {
        try {
            AnimalDAO animalDAO = new AnimalDAO();
            String sufixo = String.valueOf(System.currentTimeMillis());

            AnimalPreHistorico animalTerrestre = new AnimalTerrestrePreHistorico(
                "DAO-AT-" + sufixo,
                "Tiranossauro Rex",
                "Tyrannosaurus rex",
                Dieta.CARNIVORO,
                Porte.GRANDE,
                10
            );
            AnimalPreHistorico animalAquatico = new AnimalAquaticoPreHistorico(
                "DAO-AA-" + sufixo,
                "Mosassauro",
                "Mosasaurus hoffmannii",
                Dieta.CARNIVORO,
                Porte.GRANDE,
                9
            );
            AnimalPreHistorico animalAereo = new AnimalAereoPreHistorico(
                "DAO-AE-" + sufixo,
                "Pteranodonte",
                "Pteranodon longiceps",
                Dieta.CARNIVORO,
                Porte.MEDIO,
                7
            );

            animalDAO.salvar(animalTerrestre);
            animalDAO.salvar(animalAquatico);
            animalDAO.salvar(animalAereo);
            System.out.println("Animais salvos pelo AnimalDAO.");

            System.out.println();
            System.out.println("Listagem de animais:");
            List<AnimalPreHistorico> animais = animalDAO.listarTodos();
            for (AnimalPreHistorico animal : animais) {
                animal.exibirDados();
                System.out.println();
            }

            Long idBusca = animalTerrestre.getId();
            AnimalPreHistorico animalEncontrado = animalDAO.buscarPorId(idBusca);
            System.out.println("Busca por ID " + idBusca + ":");
            if (animalEncontrado != null) {
                animalEncontrado.exibirDados();
            }
            else {
                System.out.println("Animal nao encontrado.");
            }

            System.out.println();
            Long idRemocao = animalAereo.getId();
            animalDAO.remover(idRemocao);
            System.out.println("Animal removido pelo ID " + idRemocao + ".");

            System.out.println();
            System.out.println("Listagem apos remocao:");
            List<AnimalPreHistorico> animaisAposRemocao = animalDAO.listarTodos();
            for (AnimalPreHistorico animal : animaisAposRemocao) {
                animal.exibirDados();
                System.out.println();
            }
        }
        finally {
            JPAUtil.close();
        }
    }
}
