package br.ufrn.imd.utravel.exception;

public class EntidadeNaoEncontradaException extends javax.persistence.EntityNotFoundException {
    public EntidadeNaoEncontradaException() {
    }

    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }
}
