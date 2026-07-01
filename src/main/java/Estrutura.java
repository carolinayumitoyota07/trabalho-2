/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author carol
 */
public abstract class Estrutura {

    private String numeracao;
    private int numeroFuncionariosResponsaveis;

    public Estrutura(String numeracao, int numeroFuncionariosResponsaveis) {
        setNumeracao(numeracao);
        setNumeroFuncionariosResponsaveis(numeroFuncionariosResponsaveis);
    }

    public String getNumeracao() {
        return this.numeracao;
    }

    public int getNumeroFuncionariosResponsaveis() {
        return this.numeroFuncionariosResponsaveis;
    }

    public void setNumeracao(String numeracao) {
        if (numeracao == null || numeracao.trim().isEmpty()) {
            System.out.println("Erro: A numeracao nao pode ser nula ou vazia.");
        }
        else {
            this.numeracao = numeracao;
        }
    }

    public void setNumeroFuncionariosResponsaveis(int numeroFuncionariosResponsaveis) {
        if (numeroFuncionariosResponsaveis < 0) {
            System.out.println("Erro: O numero de funcionarios deve ser maior ou igual a zero.");
        }
        else {
            this.numeroFuncionariosResponsaveis = numeroFuncionariosResponsaveis;
        }
    }

    public void exibirDados() {
        System.out.println("Numeracao: " + this.numeracao);
        System.out.println("Funcionarios Responsaveis: " + this.numeroFuncionariosResponsaveis);
    }
}