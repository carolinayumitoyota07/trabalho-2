/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author carol
 */
public class AnimalAquaticoPreHistorico extends AnimalPreHistorico {
    private int dificuldadeContencaoAquatica;
    
    public AnimalAquaticoPreHistorico(
        String codigo,
        String nome,
        String especie,
        Dieta dieta,
        Porte porte,
        int dificuldadeContencaoAquatica
    ){
        super(codigo, nome, especie, dieta, porte);
        setDificuldadeContencaoAquatica(dificuldadeContencaoAquatica);
    }
    
    public int getDificuldadeContencaoAquatica() {
        return this.dificuldadeContencaoAquatica;
    }
    
    public void setDificuldadeContencaoAquatica(
        int dificuldadeContencaoAquatica
    ) {
        if (
            dificuldadeContencaoAquatica >= 0 &&
            dificuldadeContencaoAquatica <= 10
        ) {
            this.dificuldadeContencaoAquatica =
                dificuldadeContencaoAquatica;
        }
        else {
            System.out.println(
                "Dificuldade de contencao aquatica invalida!"
            );
    }
    }
    
    @Override
    public GrauPerigo calcularGrauPerigo() {
        int pontuacao = 0;
        if (getPorte() == Porte.GRANDE) {
            pontuacao += 4;
        }
        else if (getPorte() == Porte.MEDIO) {
            pontuacao += 2;
        }
        else {
            pontuacao += 1;
        }
        if (getDieta() == Dieta.CARNIVORO) {
            pontuacao += 3;
        }
        else if (getDieta() == Dieta.ONIVORO) {
            pontuacao += 2;
        }
        else {
            pontuacao += 1;
        }
        pontuacao += this.dificuldadeContencaoAquatica;
        if (pontuacao <= 4) {
            return GrauPerigo.BAIXO;
        }
        else if (pontuacao <= 8) {
            return GrauPerigo.MODERADO;
        }
        else {
            return GrauPerigo.ALTO;
    }
    }
}
