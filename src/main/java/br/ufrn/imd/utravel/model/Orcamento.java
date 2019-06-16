package br.ufrn.imd.utravel.model;

public class Orcamento extends AbstractModel {
    private String tipo;
    private Float valorEstimado;
    private Viagem viagem;

    public Orcamento() {
    }

    public Orcamento(String tipo, Float valorEstimado, Viagem viagem) {
        this.tipo = tipo;
        this.valorEstimado = valorEstimado;
        this.viagem = viagem;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Float getValorEstimado() {
        return valorEstimado;
    }

    public void setValorEstimado(Float valorEstimado) {
        this.valorEstimado = valorEstimado;
    }

    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }
}
