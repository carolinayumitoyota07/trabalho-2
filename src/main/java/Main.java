/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author carol
 */
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Cuidador cuidador = new Cuidador(
            "Carolina Yumi Toyota",
            "22222222222",
            Genero.FEMININO,
            19,
            Turno.VESPERTINO,
            "C002"
        );

        AnimalPreHistorico animalTerrestre = new AnimalTerrestrePreHistorico(
            "AT001",
            "Tiranossauro Rex",
            "Tyrannosaurus rex",
            Dieta.CARNIVORO,
            Porte.GRANDE,
            10
        );

        AnimalPreHistorico animalAquatico = new AnimalAquaticoPreHistorico(
            "AA001",
            "Megalodonte",
            "Otodus megalodon",
            Dieta.CARNIVORO,
            Porte.GRANDE,
            10
        );

        AnimalPreHistorico animalAereo = new AnimalAereoPreHistorico(
            "AE001",
            "Pteranodonte",
            "Pteranodon longiceps",
            Dieta.CARNIVORO,
            Porte.MEDIO,
            8
        );

        ArrayList<AnimalPreHistorico> animais = new ArrayList<>();
        animais.add(animalTerrestre);
        animais.add(animalAquatico);
        animais.add(animalAereo);

        System.out.println("Demonstracao polimorfica de animais:");
        for (AnimalPreHistorico animal : animais) {
            animal.exibirDados();
            System.out.println();
        }

        JaulaTerrestre jaulaTerrestre = new JaulaTerrestre(
            "JT-01",
            1,
            cuidador,
            2,
            NivelSeguranca.ALTO,
            300.0
        );

        JaulaAquatica jaulaAquatica = new JaulaAquatica(
            "JA-01",
            1,
            cuidador,
            2,
            NivelSeguranca.ALTO,
            500.0
        );

        JaulaAerea jaulaAerea = new JaulaAerea(
            "JAR-01",
            1,
            cuidador,
            2,
            NivelSeguranca.MODERADO,
            40.0
        );

        jaulaTerrestre.adicionarAnimal(animalTerrestre);
        jaulaAquatica.adicionarAnimal(animalAquatico);
        jaulaAerea.adicionarAnimal(animalAereo);

        System.out.println("Tentativa de alocacao incompativel:");
        jaulaTerrestre.adicionarAnimal(animalAquatico);
        System.out.println();

        System.out.println("Animais alocados na jaula terrestre: "
            + jaulaTerrestre.getAnimaisAlocados().size());
        jaulaTerrestre.exibirDados();

        ArrayList<OperacaoParque> operacoes = new ArrayList<>();
        operacoes.add(new Alimentacao(
            cuidador,
            "08:00",
            "14/05/2026",
            jaulaTerrestre,
            animalTerrestre,
            "Carne"
        ));
        operacoes.add(new CheckUpAnimal(
            cuidador,
            "10:00",
            "14/05/2026",
            jaulaTerrestre,
            animalTerrestre,
            "Animal em boas condicoes"
        ));

        System.out.println();
        System.out.println("Demonstracao polimorfica de operacoes:");
        for (OperacaoParque operacao : operacoes) {
            operacao.registrarOperacao();
            System.out.println();
        }
    }
}