package br.ufrn.imd.utravel.model;

public class Usuario extends AbstractModel {
    private String email;
    private String senha;
    private String telefone;
    private Pessoa pessoa;

    public Usuario() {}

    public Usuario(Long id, String email, String senha, String telefone, Pessoa pessoa) {
        setId(id);
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
