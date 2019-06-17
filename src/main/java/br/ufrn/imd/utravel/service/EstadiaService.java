package br.ufrn.imd.utravel.service;

import br.ufrn.imd.utravel.exception.EntidadeNaoEncontradaException;
import br.ufrn.imd.utravel.model.Estadia;
import br.ufrn.imd.utravel.repository.EstadiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadiaService implements GenericService<Estadia>{
    private final EstadiaRepository estadiaRepository;

    @Autowired
    public EstadiaService(EstadiaRepository estadiaRepository) {
        this.estadiaRepository = estadiaRepository;
    }

    @Override
    public List<Estadia> findAll() {
        return estadiaRepository.findAll();
    }

    @Override
    public Optional<Estadia> findById(Integer id) {
        return estadiaRepository.findById(id);
    }

    @Override
    public Estadia save(Estadia estadia) {
        return estadiaRepository.save(estadia);
    }

    @Override
    public Estadia update(Integer id, Estadia estadia) {
        Optional<Estadia> estadiaFind = estadiaRepository.findById(id);
        if (!estadiaFind.isPresent()){
            throw new EntidadeNaoEncontradaException("Não foi possível encontrar uma estadia com este id.");
        }

        estadia.setId(id);
        estadia.setCodigo(estadiaFind.get().getCodigo());
        estadia.setViagemDestino(estadiaFind.get().getViagemDestino());

        return estadiaRepository.update(estadia);
    }

    @Override
    public String delete(Integer id) {
        return estadiaRepository.delete(id);
    }
}
