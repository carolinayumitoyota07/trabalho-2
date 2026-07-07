package model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
@Entity
@DiscriminatorValue("AQUATICA")
public class JaulaAquatica extends Jaula {

    private double metrosCubicosAgua;

    protected JaulaAquatica() {
    }

    public JaulaAquatica(
        String numeracao,
        int capacidade,
        NivelSeguranca nivelSeguranca,
        double metrosCubicosAgua
    ) {
        super(
            numeracao,
            capacidade,
            nivelSeguranca
        );

        setMetrosCubicosAgua(metrosCubicosAgua);
    }

    public double getMetrosCubicosAgua() {
        return this.metrosCubicosAgua;
    }

    public void setMetrosCubicosAgua(double metrosCubicosAgua) {
        if (metrosCubicosAgua <= 0) {
            System.out.println("Erro: Os metros cubicos de agua devem ser maiores que zero.");
        } 
        else {
            this.metrosCubicosAgua = metrosCubicosAgua;
    }
    }

    @Override
    public boolean verificarCompatibilidadeAnimal(AnimalPreHistorico animal) {
        return animal instanceof AnimalAquaticoPreHistorico;
    }

    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Metros cubicos de agua: " + this.metrosCubicosAgua);
}
}
