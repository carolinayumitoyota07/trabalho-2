/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author carol
 */
public class CheckUpAnimal extends OperacaoParque {

    private AnimalPreHistorico animal;
    private String observacaoClinica;

    public CheckUpAnimal(
        Cuidador funcionarioResponsavel,
        String horario,
        String data,
        Estrutura estrutura,
        AnimalPreHistorico animal,
        String observacaoClinica
    ) {
        super(funcionarioResponsavel, horario, data, estrutura);

        setAnimal(animal);
        setObservacaoClinica(observacaoClinica);
    }

    public AnimalPreHistorico getAnimal() {
        return this.animal;
    }

    public String getObservacaoClinica() {
        return this.observacaoClinica;
    }

    public void setAnimal(AnimalPreHistorico animal) {
        if (animal == null) {
            System.out.println("Erro: O animal nao pode ser nulo.");
        } 
        else {
            this.animal = animal;
    }
    }

    public void setObservacaoClinica(String observacaoClinica) {
        if (observacaoClinica == null || observacaoClinica.trim().isEmpty()) {
            System.out.println("Erro: A observacao clinica nao pode ser nula ou vazia.");
        } 
        else {
            this.observacaoClinica = observacaoClinica;
    }
    }

    @Override
    public void setFuncionarioResponsavel(Funcionario funcionarioResponsavel) {
        if (funcionarioResponsavel == null) {
            System.out.println("Erro: O funcionario responsavel nao pode ser nulo.");
        } 
        else if (!(funcionarioResponsavel instanceof Cuidador)) {
            System.out.println("Erro: O check-up animal deve ser realizado por um cuidador.");
        } 
        else {
            super.setFuncionarioResponsavel(funcionarioResponsavel);
    }
    }

    @Override
    public void registrarOperacao() {
        System.out.println("Check-up animal registrado.");
        System.out.println("Data: " + getData());
        System.out.println("Horario: " + getHorario());
        System.out.println("Observacao clinica: " + this.observacaoClinica);

        System.out.println("Funcionario responsavel:");
        getFuncionarioResponsavel().exibirDados();

        System.out.println("Estrutura:");
        getEstrutura().exibirDados();

        System.out.println("Animal avaliado:");
        this.animal.exibirDados();
    }

    @Override
    public void exibirDados() {
        super.exibirDados();

        System.out.println("Animal avaliado:");
        this.animal.exibirDados();

        System.out.println("Observacao clinica: " + this.observacaoClinica);
}
}