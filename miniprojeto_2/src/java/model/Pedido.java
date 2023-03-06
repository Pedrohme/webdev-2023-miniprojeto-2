package model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private String nomeCliente;
    private String rua;
    private String bairro;
    private String numero;
    private List<LanchePedido> lanches;

    public List<LanchePedido> getLanches() {
        return lanches;
    }

    public void setLanches(List<LanchePedido> lanches) {
        this.lanches = lanches;
    }

    public Pedido(int id, String nomeCliente, String rua, String bairro, String numero) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.lanches = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
