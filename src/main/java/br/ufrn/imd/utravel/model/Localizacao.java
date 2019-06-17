package br.ufrn.imd.utravel.model;

public class Localizacao extends AbstractModel {
    private String pais;
    private String estado;
    private String cidade;

    public Localizacao() {
    }

    public Localizacao(Integer id, String pais, String estado, String cidade) {
        super.setId(id);
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
