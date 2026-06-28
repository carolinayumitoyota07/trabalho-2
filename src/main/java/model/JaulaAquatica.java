package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author carol
 */
public class JaulaAquatica extends Jaula {

    private double metrosCubicosAgua;

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
