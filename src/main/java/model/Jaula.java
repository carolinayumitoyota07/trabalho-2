package model;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author carol
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_jaula")
public abstract class Jaula {

    @Id
    @GeneratedValue
    private Long id;

    private String numeracao;

    @OneToMany
    @JoinTable(
        name = "jaula_animais",
        joinColumns = @JoinColumn(name = "jaula_id"),
        inverseJoinColumns = @JoinColumn(name = "animal_id")
    )
    private List<AnimalPreHistorico> animaisAlocados;

    private int capacidade;

    @Enumerated(EnumType.STRING)
    private NivelSeguranca nivelSeguranca;

    protected Jaula() {
        this.animaisAlocados = new ArrayList<>();
    }

    public Jaula(
        String numeracao,
        int capacidade,
        NivelSeguranca nivelSeguranca
    ) {
        this.animaisAlocados = new ArrayList<>();

        setNumeracao(numeracao);
        setCapacidade(capacidade);
        setNivelSeguranca(nivelSeguranca);
    }

    public Long getId() {
        return this.id;
    }

    public String getNumeracao() {
        return this.numeracao;
    }

    public List<AnimalPreHistorico> getAnimaisAlocados() {
        return Collections.unmodifiableList(new ArrayList<>(this.animaisAlocados));
    }

    public int getCapacidade() {
        return this.capacidade;
    }

    public NivelSeguranca getNivelSeguranca() {
        return this.nivelSeguranca;
    }

    public void setNumeracao(String numeracao) {
        if (numeracao == null || numeracao.isEmpty()) {
            System.out.println("Erro: A numeracao da jaula nao pode ser vazia.");
        }
        else {
            this.numeracao = numeracao;
        }
    }

    public void setCapacidade(int capacidade) {
        if (capacidade <= 0) {
            System.out.println("Erro: A capacidade da jaula deve ser maior que zero.");
        }
        else if (this.animaisAlocados != null && this.animaisAlocados.size() > capacidade) {
            System.out.println("Erro: A nova capacidade nao pode ser menor que a quantidade de animais ja alocados.");
        }
        else {
            this.capacidade = capacidade;
        }
    }

    public void setNivelSeguranca(NivelSeguranca nivelSeguranca) {
        if (nivelSeguranca == null) {
            System.out.println("Erro: O nivel de seguranca nao pode ser nulo.");
        }
        else {
            this.nivelSeguranca = nivelSeguranca;
        }
    }

    public boolean verificarCapacidadeDisponivel() {
        return this.animaisAlocados.size() < this.capacidade;
    }

    public abstract boolean verificarCompatibilidadeAnimal(AnimalPreHistorico animal);

    public void adicionarAnimal(AnimalPreHistorico animal) {
        if (animal == null) {
            System.out.println("Erro: O animal nao pode ser nulo.");
        }
        else if (this.animaisAlocados.contains(animal)) {
            System.out.println("Erro: Esse animal ja esta alocado nesta jaula.");
        }
        else if (!verificarCapacidadeDisponivel()) {
            System.out.println("Erro: A jaula ja atingiu sua capacidade maxima.");
        }
        else if (!verificarCompatibilidadeAnimal(animal)) {
            System.out.println("Erro: O animal nao e compativel com este tipo de jaula.");
        }
        else {
            this.animaisAlocados.add(animal);
        }
    }

    public void removerAnimal(AnimalPreHistorico animal) {
        if (animal == null) {
            System.out.println("Erro: O animal nao pode ser nulo.");
        }
        else if (!this.animaisAlocados.contains(animal)) {
            System.out.println("Erro: Esse animal nao esta alocado nesta jaula.");
        }
        else {
            this.animaisAlocados.remove(animal);
        }
    }

    public void exibirDados() {
        System.out.println("Numeracao: " + this.numeracao);
        System.out.println("Capacidade: " + this.capacidade);
        System.out.println("Nivel de seguranca: " + this.nivelSeguranca);

        System.out.println("Animais alocados:");
        if (this.animaisAlocados.isEmpty()) {
            System.out.println("Nenhum animal alocado.");
        }
        else {
            for (AnimalPreHistorico animal : this.animaisAlocados) {
                animal.exibirDados();
            }
        }
    }
}
