package br.ufrn.imd.utravel.model;

public class Pessoa extends EntidadeAbstrada {
    private String cpf;
    private String nome;

    public Pessoa() {}

    public Pessoa(Long id, String cpf, String nome) {
        setId(id);
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
