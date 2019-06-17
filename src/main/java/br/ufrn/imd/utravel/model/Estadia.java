package br.ufrn.imd.utravel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Estadia extends AbstractModel {
    private String codigo;
    private String endereco;
    private String tipo;
    private Integer quantidadeQuartos;
    private Float valorPago;
    private ViagemDestino viagemDestino;

    public Estadia() {
    }

    public Estadia(String codigo, String endereco, String tipo, Integer quantidadeQuartos, Float valorPago, ViagemDestino viagemDestino) {
        this.codigo = codigo;
        this.endereco = endereco;
        this.tipo = tipo;
        this.quantidadeQuartos = quantidadeQuartos;
        this.valorPago = valorPago;
        this.viagemDestino = viagemDestino;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getQuantidadeQuartos() {
        return quantidadeQuartos;
    }

    public void setQuantidadeQuartos(Integer quantidadeQuartos) {
        this.quantidadeQuartos = quantidadeQuartos;
    }

    public Float getValorPago() {
        return valorPago;
    }

    public void setValorPago(Float valorPago) {
        this.valorPago = valorPago;
    }

    @JsonIgnore
    public ViagemDestino getViagemDestino() {
        return viagemDestino;
    }

    public void setViagemDestino(ViagemDestino viagemDestino) {
        this.viagemDestino = viagemDestino;
    }
}
