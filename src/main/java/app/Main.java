package app;
import java.util.ArrayList;
import model.*;

public class Main {

    public static void main(String[] args) {
        AnimalTerrestrePreHistorico tiranossauro = new AnimalTerrestrePreHistorico(
            "AT001",
            "Tiranossauro Rex",
            "Tyrannosaurus rex",
            Dieta.CARNIVORO,
            Porte.GRANDE,
            10
        );

        AnimalAquaticoPreHistorico mosassauro = new AnimalAquaticoPreHistorico(
            "AA001",
            "Mosassauro",
            "Mosasaurus hoffmannii",
            Dieta.CARNIVORO,
            Porte.GRANDE,
            9
        );

        AnimalAereoPreHistorico pteranodonte = new AnimalAereoPreHistorico(
            "AE001",
            "Pteranodonte",
            "Pteranodon longiceps",
            Dieta.CARNIVORO,
            Porte.MEDIO,
            7
        );

        ArrayList<AnimalPreHistorico> animais = new ArrayList<>();
        animais.add(tiranossauro);
        animais.add(mosassauro);
        animais.add(pteranodonte);

        System.out.println("=== Polimorfismo com AnimalPreHistorico ===");
        for (AnimalPreHistorico animal : animais) {
            animal.exibirDados();
            System.out.println();
        }

        JaulaTerrestre jaulaTerrestre = new JaulaTerrestre(
            "JT-001",
            2,
            NivelSeguranca.ALTO,
            500.0
        );

        JaulaAquatica jaulaAquatica = new JaulaAquatica(
            "JA-001",
            2,
            NivelSeguranca.ALTO,
            800.0
        );

        JaulaAerea jaulaAerea = new JaulaAerea(
            "JAE-001",
            2,
            NivelSeguranca.MODERADO,
            80.0
        );

        System.out.println("=== Alocacoes compativeis ===");
        jaulaTerrestre.adicionarAnimal(tiranossauro);
        jaulaAquatica.adicionarAnimal(mosassauro);
        jaulaAerea.adicionarAnimal(pteranodonte);

        System.out.println("=== Tentativa de alocacao incompativel ===");
        jaulaTerrestre.adicionarAnimal(mosassauro);
        System.out.println(
            "Mosassauro esta na jaula terrestre? " +
            (jaulaTerrestre.getAnimaisAlocados().contains(mosassauro) ? "Sim" : "Nao")
        );

        System.out.println();
        System.out.println("=== Estado final das jaulas ===");
        jaulaTerrestre.exibirDados();
        System.out.println();
        jaulaAquatica.exibirDados();
        System.out.println();
        jaulaAerea.exibirDados();
    }
}
