/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author carol
 */
public class Alimentacao extends OperacaoParque {

    private AnimalPreHistorico animal;
    private String alimento;

    public Alimentacao(
        Cuidador funcionarioResponsavel,
        String horario,
        String data,
        Estrutura estrutura,
        AnimalPreHistorico animal,
        String alimento
    ) {
        super(funcionarioResponsavel, horario, data, estrutura);

        setAnimal(animal);
        setAlimento(alimento);
    }

    public AnimalPreHistorico getAnimal() {
        return this.animal;
    }

    public String getAlimento() {
        return this.alimento;
    }

    @Override
    public void setFuncionarioResponsavel(Funcionario funcionarioResponsavel) {
        if (funcionarioResponsavel == null) {
            System.out.println("Erro: O funcionario responsavel nao pode ser nulo.");
        } 
        else if (!(funcionarioResponsavel instanceof Cuidador)) {
            System.out.println("Erro: A alimentacao deve ser realizada por um cuidador.");
        } 
        else {
            super.setFuncionarioResponsavel(funcionarioResponsavel);
    }
    }

    public void setAnimal(AnimalPreHistorico animal) {
        if (animal == null) {
            System.out.println("Erro: O animal nao pode ser nulo.");
        } 
        else {
            this.animal = animal;
    }
    }

    public void setAlimento(String alimento) {
        if (alimento == null || alimento.trim().isEmpty()) {
            System.out.println("Erro: O alimento nao pode ser nulo ou vazio.");
        } 
        else {
            this.alimento = alimento;
    }
    }

    @Override
    public void registrarOperacao() {
        System.out.println("Operacao de alimentacao registrada.");
        System.out.println("Data: " + getData());
        System.out.println("Horario: " + getHorario());
        System.out.println("Alimento fornecido: " + this.alimento);

        System.out.println("Funcionario responsavel:");
        getFuncionarioResponsavel().exibirDados();

        System.out.println("Estrutura:");
        getEstrutura().exibirDados();

        System.out.println("Animal alimentado:");
        this.animal.exibirDados();
    }

    @Override
    public void exibirDados() {
        super.exibirDados();

        System.out.println("Animal alimentado:");
        this.animal.exibirDados();

        System.out.println("Alimento: " + this.alimento);
}
}