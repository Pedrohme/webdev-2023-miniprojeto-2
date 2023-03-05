package model;

public class Pedido {
    private int id;
    private int nomeCliente;
    private String rua;
    private String bairro;
    private String numero;

    public Pedido(int id, int nomeCliente, String rua, String bairro, String numero) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(int nomeCliente) {
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
