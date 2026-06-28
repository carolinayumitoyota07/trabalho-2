package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author carol
 */
public class JaulaAerea extends Jaula {

    private double metrosAltura;

    public JaulaAerea(
        String numeracao,
        int capacidade,
        NivelSeguranca nivelSeguranca,
        double metrosAltura
    ) {
        super(
            numeracao,
            capacidade,
            nivelSeguranca
        );

        setMetrosAltura(metrosAltura);
    }

    public double getMetrosAltura() {
        return this.metrosAltura;
    }

    public void setMetrosAltura(double metrosAltura) {
        if (metrosAltura <= 0) {
            System.out.println("Erro: Os metros de altura devem ser maiores que zero.");
        } 
        else {
            this.metrosAltura = metrosAltura;
    }
    }

    @Override
    public boolean verificarCompatibilidadeAnimal(AnimalPreHistorico animal) {
        return animal instanceof AnimalAereoPreHistorico;
    }

    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Metros de altura: " + this.metrosAltura);
    }
}

