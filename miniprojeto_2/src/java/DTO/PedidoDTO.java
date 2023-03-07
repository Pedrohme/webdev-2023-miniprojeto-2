package DTO;

import java.util.List;

public class PedidoDTO {
    private String nome;
    private String rua;
    private String bairro;
    private String numero;
    private List<LanchePedidoDTO> itens;

    public PedidoDTO(String nome, String rua, String bairro, String numero, List<LanchePedidoDTO> itens) {
        this.nome = nome;
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.itens = itens;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public List<LanchePedidoDTO> getItens() {
        return itens;
    }

    public void setItens(List<LanchePedidoDTO> itens) {
        this.itens = itens;
    }
}
