/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author carol
 */
public abstract class Funcionario extends Pessoa {

    private Turno turno;
    private String matricula;

    public Funcionario(String nome, String cpf, Genero genero, int idade, Turno turno, String matricula) {
        super(nome, cpf, genero, idade);
        setTurno(turno);
        setMatricula(matricula);
    }

    public Turno getTurno() {
        return this.turno;
    }

    public String getMatricula() {
        return this.matricula;
    }

    public void setTurno(Turno turno) {
        if (turno == null) {
            System.out.println("Erro: O turno nao pode ser nulo.");
        }
        else {
            this.turno = turno;
        }
    }

    public void setMatricula(String matricula) {
        if (matricula == null || matricula.trim().isEmpty()) {
            System.out.println("Erro: A matricula nao pode ser nula ou vazia.");
        }
        else {
            this.matricula = matricula;
        }
    }

    @Override
    public void exibirDados() {
        super.exibirDados();
        System.out.println("Turno: " + this.turno);
        System.out.println("Matricula: " + this.matricula);
    }
}