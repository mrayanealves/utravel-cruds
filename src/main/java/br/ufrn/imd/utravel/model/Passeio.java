package br.ufrn.imd.utravel.model;

public class Passeio extends AbstractModel {
    private String tipo;
    private String endereco;
    private String empresa;
    // TODO: localizacao_id


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
}
