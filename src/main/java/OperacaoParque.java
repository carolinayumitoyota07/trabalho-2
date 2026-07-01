/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author carol
 */
public abstract class OperacaoParque {

    private Funcionario funcionarioResponsavel;
    private String horario;
    private String data;
    private Estrutura estrutura;

    public OperacaoParque(
        Funcionario funcionarioResponsavel,
        String horario,
        String data,
        Estrutura estrutura
    ) {
        setFuncionarioResponsavel(funcionarioResponsavel);
        setHorario(horario);
        setData(data);
        setEstrutura(estrutura);
    }

    public Funcionario getFuncionarioResponsavel() {
        return this.funcionarioResponsavel;
    }

    public String getHorario() {
        return this.horario;
    }

    public String getData() {
        return this.data;
    }

    public Estrutura getEstrutura() {
        return this.estrutura;
    }

    public void setFuncionarioResponsavel(Funcionario funcionarioResponsavel) {
        if (funcionarioResponsavel == null) {
            System.out.println("Erro: O funcionario responsavel nao pode ser nulo.");
        }
        else {
            this.funcionarioResponsavel = funcionarioResponsavel;
        }
    }

    public void setHorario(String horario) {
        if (horario == null || horario.trim().isEmpty()) {
            System.out.println("Erro: O horario nao pode ser nulo ou vazio.");
        }
        else {
            this.horario = horario;
        }
    }

    public void setData(String data) {
        if (data == null || data.trim().isEmpty()) {
            System.out.println("Erro: A data nao pode ser nula ou vazia.");
        }
        else {
            this.data = data;
        }
    }

    public void setEstrutura(Estrutura estrutura) {
        if (estrutura == null) {
            System.out.println("Erro: A estrutura nao pode ser nula.");
        }
        else {
            this.estrutura = estrutura;
        }
    }

    public abstract void registrarOperacao();

    public void exibirDados() {
        System.out.println("Funcionario responsavel:");
        this.funcionarioResponsavel.exibirDados();

        System.out.println("Horario: " + this.horario);
        System.out.println("Data: " + this.data);

        System.out.println("Estrutura:");
        this.estrutura.exibirDados();
    }
}