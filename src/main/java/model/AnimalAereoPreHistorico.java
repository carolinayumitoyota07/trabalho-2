package model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author carol
 */
@Entity
@DiscriminatorValue("AEREO")
public class AnimalAereoPreHistorico extends AnimalPreHistorico {
    private int riscoFugaAerea;

    protected AnimalAereoPreHistorico() {
    }
    
    public AnimalAereoPreHistorico(
        String codigo,
        String nome,
        String especie,
        Dieta dieta,
        Porte porte,
        int riscoFugaAerea
    ){
        super(codigo, nome, especie, dieta, porte);
        setRiscoFugaAerea(riscoFugaAerea);
    }
    
    public int getRiscoFugaAerea() {
        return this.riscoFugaAerea;
    }
    
    public void setRiscoFugaAerea(int riscoFugaAerea) {
        if (riscoFugaAerea >= 0 && riscoFugaAerea <= 10) {
            this.riscoFugaAerea = riscoFugaAerea;
        }
        else {
            System.out.println("Risco de fuga aerea invalido!");
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
        pontuacao += this.riscoFugaAerea;
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
