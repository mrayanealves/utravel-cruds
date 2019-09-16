package br.ufrn.imd.utravel.service;

import br.ufrn.imd.utravel.exception.EntidadeNaoEncontradaException;
import br.ufrn.imd.utravel.model.Reserva;
import br.ufrn.imd.utravel.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService implements GenericService<Reserva> {
    private final ReservaRepository reservaRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Override
    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    @Override
    public Optional<Reserva> findById(Integer id) {
        return reservaRepository.findById(id);
    }

    @Override
    public Reserva save(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public Reserva update(Integer id, Reserva reserva) {
        Optional<Reserva> reservaFind = findById(id);

        if (!reservaFind.isPresent()){
            throw new EntidadeNaoEncontradaException("Não foi possível encontrar uma reserva com este id.");
        }

        return reservaRepository.update(reserva);
    }

    @Override
    public String delete(Integer id) {
        return reservaRepository.delete(id);
    }
}
