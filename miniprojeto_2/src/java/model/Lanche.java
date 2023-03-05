package model;

import java.util.ArrayList;
import java.util.List;

public class Lanche {
    private int id;
    private String nome;
    private List<Ingrediente> ingredientes;
    private List<Ingrediente> adicionados;
    private List<Ingrediente> removidos;

    public Lanche(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.ingredientes = new ArrayList<Ingrediente>();
    }

    public Lanche(int id, String nome, List<Ingrediente> ingredientes, List<Ingrediente> adicionados, List<Ingrediente> removidos) {
        this.id = id;
        this.nome = nome;
        this.ingredientes = ingredientes;
        this.adicionados = adicionados;
        this.removidos = removidos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public List<Ingrediente> getAdicionados() {
        return adicionados;
    }

    public void setAdicionados(List<Ingrediente> adicionados) {
        this.adicionados = adicionados;
    }

    public List<Ingrediente> getRemovidos() {
        return removidos;
    }

    public void setRemovidos(List<Ingrediente> removidos) {
        this.removidos = removidos;
    }

    public void addIngrediente(Ingrediente ingrediente) {
        this.ingredientes.add(ingrediente);
    }
}
