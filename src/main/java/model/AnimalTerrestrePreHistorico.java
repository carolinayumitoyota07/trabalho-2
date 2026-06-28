package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author carol
 */
public class AnimalTerrestrePreHistorico extends AnimalPreHistorico {
    private int forcaFisica;
    
    public AnimalTerrestrePreHistorico(
        String codigo,
        String nome,
        String especie,
        Dieta dieta,
        Porte porte,
        int forcaFisica
    ){
        super(codigo, nome, especie, dieta, porte);
        setForcaFisica(forcaFisica);
    }
    
    public int getForcaFisica() {
        return this.forcaFisica;
    }
    
    public void setForcaFisica(int forcaFisica) {
        if (forcaFisica >= 0 && forcaFisica <= 10) {
            this.forcaFisica = forcaFisica;
        }
        else {
            System.out.println("forca fisica invalida!");
    }
    }
    
    @Override
    public GrauPerigo calcularGrauPerigo() {
        int pontuacao = 0;
        if (getPorte() == Porte.GRANDE) {
            pontuacao += 3;
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
        pontuacao += this.forcaFisica;
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
