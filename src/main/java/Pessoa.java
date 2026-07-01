/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author carol
 */
public abstract class Pessoa {

    private String nome;
    private String cpf;
    private Genero genero;
    private int idade;

    public Pessoa(String nome, String cpf, Genero genero, int idade) {
        setNome(nome);
        setCpf(cpf);
        setGenero(genero);
        setIdade(idade);
    }

    public String getNome() {
        return this.nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public Genero getGenero() {
        return this.genero;
    }

    public int getIdade() {
        return this.idade;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Erro: O nome nao pode ser nulo ou vazio.");
        }
        else {
            this.nome = nome;
        }
    }

    public void setCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            System.out.println("Erro: O CPF nao pode ser nulo ou vazio.");
        }
        else {
            this.cpf = cpf;
        }
    }

    public void setGenero(Genero genero) {
        if (genero == null) {
            System.out.println("Erro: O genero nao pode ser nulo.");
        }
        else {
            this.genero = genero;
        }
    }

    public void setIdade(int idade) {
        if (idade < 0) {
            System.out.println("Erro: A idade deve ser maior ou igual a zero.");
        }
        else {
            this.idade = idade;
        }
    }

    public void exibirDados() {
        System.out.println("Nome: " + this.nome);
        System.out.println("CPF: " + this.cpf);
        System.out.println("Genero: " + this.genero);
        System.out.println("Idade: " + this.idade);
    }
}