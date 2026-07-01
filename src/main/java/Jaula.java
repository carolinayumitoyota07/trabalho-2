/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author carol
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Jaula extends Estrutura {

    private Cuidador cuidadorResponsavel;
    private ArrayList<AnimalPreHistorico> animaisAlocados;
    private int capacidade;
    private NivelSeguranca nivelSeguranca;

    public Jaula(
        String numeracao,
        int numeroFuncionariosResponsaveis,
        Cuidador cuidadorResponsavel,
        int capacidade,
        NivelSeguranca nivelSeguranca
    ) {
        super(numeracao, numeroFuncionariosResponsaveis);

        this.animaisAlocados = new ArrayList<>();

        setCuidadorResponsavel(cuidadorResponsavel);
        setCapacidade(capacidade);
        setNivelSeguranca(nivelSeguranca);
    }

    public Cuidador getCuidadorResponsavel() {
        return this.cuidadorResponsavel;
    }

    public List<AnimalPreHistorico> getAnimaisAlocados() {
        return Collections.unmodifiableList(this.animaisAlocados);
    }

    public int getCapacidade() {
        return this.capacidade;
    }

    public NivelSeguranca getNivelSeguranca() {
        return this.nivelSeguranca;
    }

    public void setCuidadorResponsavel(Cuidador cuidadorResponsavel) {
        if (cuidadorResponsavel == null) {
            System.out.println("Erro: O cuidador responsavel nao pode ser nulo.");
        }
        else {
            this.cuidadorResponsavel = cuidadorResponsavel;
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

    @Override
    public void exibirDados() {
        super.exibirDados();

        System.out.println("Cuidador responsavel:");
        this.cuidadorResponsavel.exibirDados();

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