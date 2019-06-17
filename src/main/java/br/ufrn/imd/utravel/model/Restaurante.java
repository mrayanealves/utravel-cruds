package br.ufrn.imd.utravel.model;

public class Restaurante extends AbstractModel {
    private Empresa empresa;
    private String tipo;
    private String avaliacao;
    private String endereco;

    public Restaurante() {
    }

    public Restaurante(Integer id, String tipo, String avaliacao, String endereco, Empresa empresa) {
        super.setId(id);
        this.tipo = tipo;
        this.avaliacao = avaliacao;
        this.endereco = endereco;
        this.empresa = empresa;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
