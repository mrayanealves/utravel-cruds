package br.ufrn.imd.utravel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ViagemReserva {
    private Viagem viagem;
    private Reserva reserva;

    @JsonIgnore
    public Viagem getViagem() {
        return viagem;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
}
