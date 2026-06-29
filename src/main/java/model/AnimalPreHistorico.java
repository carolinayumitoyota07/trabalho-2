package model;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import java.util.ArrayList;

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
@DiscriminatorColumn(name = "tipo_animal")
public abstract class AnimalPreHistorico {
    private static ArrayList<String> codigos = new ArrayList<String>();

    @Id
    @GeneratedValue
    private Long id;

    private String codigo;
    private String nome;
    private String especie;

    @Enumerated(EnumType.STRING)
    private Dieta dieta;

    @Enumerated(EnumType.STRING)
    private Porte porte;

    protected AnimalPreHistorico() {
    }

    public AnimalPreHistorico(
        String codigo,
        String nome,
        String especie,
        Dieta dieta,
        Porte porte
    ){
        setCodigo(codigo);
        setNome(nome);
        setEspecie(especie);
        setDieta(dieta);
        setPorte(porte);
    }

    public Long getId() {
        return this.id;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public String getEspecie() {
        return this.especie;
    }

    public Dieta getDieta() {
        return this.dieta;
    }

    public Porte getPorte() {
        return this.porte;
    }

    public GrauPerigo getGrauPerigo() {
        return calcularGrauPerigo();
    }

    public String getNome() {
        return this.nome;
    }

    public void setCodigo(String codigo) {
        if (codigo != null && !codigo.isEmpty() && !codigos.contains(codigo)) {
            this.codigo = codigo;
            codigos.add(codigo);
        }
        else {
            System.out.print("codigo invalido ou ja existente!");
        }
    }

    public void setNome(String nome) {
        if (nome != null && !nome.isEmpty()){
            this.nome = nome;
        }
        else {
            System.out.println("Nome invalido!");
        }
    }

    public void setEspecie(String especie) {
        if (especie != null && !especie.isEmpty()) {
            this.especie = especie;
        }
        else {
            System.out.print("especie invalida!");
        }
    }

    public void setDieta(Dieta dieta) {
        if (dieta != null) {
            this.dieta = dieta;
        }
        else {
            System.out.print("dieta invalida!");
        }
    }

    public void setPorte(Porte porte) {
        if (porte != null) {
            this.porte = porte;
        }
        else {
            System.out.print("porte invalido!");
        }
    }

    public abstract GrauPerigo calcularGrauPerigo();

    public void exibirDados() {
        System.out.println("Codigo: " + this.codigo);
        System.out.println("nome: " + this.nome);
        System.out.println("Especie: " + this.especie);
        System.out.println("Dieta: " + this.dieta);
        System.out.println("Porte: " + this.porte);
        System.out.println("Grau de perigo: " + getGrauPerigo());
    }
}
