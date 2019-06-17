package br.ufrn.imd.utravel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class ViagemDestino extends AbstractModel {
    private Viagem viagem;
    private Localizacao localizacao;
    private List<Estadia> estadias;

    public ViagemDestino() {
    }

    public ViagemDestino(Integer id, Viagem viagem, Localizacao localizacao) {
        super.setId(id);
        this.viagem = viagem;
        this.localizacao = localizacao;
    }

    @JsonIgnore
    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public List<Estadia> getEstadias() {
        return estadias;
    }

    public void setEstadias(List<Estadia> estadias) {
        this.estadias = estadias;
    }
}
