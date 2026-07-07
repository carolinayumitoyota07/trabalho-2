package model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
@Entity
@DiscriminatorValue("TERRESTRE")
public class JaulaTerrestre extends Jaula {

    private double metrosCubicos;

    protected JaulaTerrestre() {
    }

    public JaulaTerrestre(
        String numeracao,
        int capacidade,
        NivelSeguranca nivelSeguranca,
        double metrosCubicos
    ) {
        super(
            numeracao,
            capacidade,
            nivelSeguranca
        );

        setMetrosCubicos(metrosCubicos);
    }

    public double getMetrosCubicos() {
        return this.metrosCubicos;
    }

    public void setMetrosCubicos(double metrosCubicos) {
        if (metrosCubicos <= 0) {
            System.out.println("Erro: Os metros cubicos devem ser maiores que zero.");
        } 
        else {
            this.metrosCubicos = metrosCubicos;
    }
    }

    @Override
    public boolean verificarCompatibilidadeAnimal(AnimalPreHistorico animal) {
        return animal instanceof AnimalTerrestrePreHistorico;
    }

    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Metros cubicos: " + this.metrosCubicos);
    }
}
