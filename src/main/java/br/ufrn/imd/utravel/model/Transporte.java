package br.ufrn.imd.utravel.model;

public class Transporte extends AbstractModel {
    private Viagem viagem;
    private String tipo;
    private int proprio;

    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getProprio() {
        return proprio;
    }

    public void setProprio(int proprio) {
        this.proprio = proprio;
    }
}
