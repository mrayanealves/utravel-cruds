package br.ufrn.imd.utravel.model;

public class Usuario extends EntidadeAbstrada {
    private String email;
    private String senha;
    private String telefone;

    public Usuario() {}

    public Usuario(Long id, String email, String senha, String telefone) {
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
}
