package br.ufrn.imd.utravel.model;

import java.time.LocalDate;

public class Viagem extends AbstractModel {
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public Viagem() {
    }

    public Viagem(Integer id, LocalDate dataInicio, LocalDate dataFim) {
        super.setId(id);
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
}
