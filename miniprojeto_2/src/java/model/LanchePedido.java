package model;

public class LanchePedido {
    private int id;
    private Lanche lanche;

    public LanchePedido(int id, Lanche lanche) {
        this.id = id;
        this.lanche = lanche;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Lanche getLanche() {
        return lanche;
    }

    public void setLanche(Lanche lanche) {
        this.lanche = lanche;
    }

    public void addAdicionado(Ingrediente ingrediente) {
        this.lanche.addAdicionado(ingrediente);
    }

    public void addRemovido(Ingrediente ingrediente) {
        this.lanche.addRemovido(ingrediente);
    }
}
