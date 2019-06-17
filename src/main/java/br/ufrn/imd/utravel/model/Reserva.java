package br.ufrn.imd.utravel.model;

import java.time.LocalDate;

public class Reserva extends AbstractModel {
    private Long numero;
    private LocalDate data;
    private String mesa;
    private Restaurante restaurante;

    public Reserva() {
    }


    public Reserva(Long numero, LocalDate data, String mesa, Restaurante restaurante) {
        this.numero = numero;
        this.data = data;
        this.mesa = mesa;
        this.restaurante = restaurante;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
}
