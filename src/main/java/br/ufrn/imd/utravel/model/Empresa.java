package br.ufrn.imd.utravel.model;

public class Empresa extends AbstractModel {
    private String cnpj;
    private String nome;

    public Empresa() {
    }

    public Empresa(Integer id, String cnpj, String nome) {
        super.setId(id);
        this.cnpj = cnpj;
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
