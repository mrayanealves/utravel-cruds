package br.ufrn.imd.utravel.service;

import br.ufrn.imd.utravel.model.Viagem;
import br.ufrn.imd.utravel.repository.ViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ViagemService implements GenericService<Viagem> {
    private final ViagemRepository viagemRepository;

    @Autowired
    public ViagemService(ViagemRepository viagemRepository) {
        this.viagemRepository = viagemRepository;
    }

    @Override
    public List<Viagem> findAll() {
        return viagemRepository.findAll();
    }

    @Override
    public Optional<Viagem> findById(Integer id) {
        return viagemRepository.findById(id);
    }

    @Override
    public Viagem save(Viagem viagem) {
        return viagemRepository.save(viagem);
    }

    @Override
    public Viagem update(Integer id, Viagem viagem) {
        viagem.setId(id);
        return viagemRepository.update(viagem);
    }

    @Override
    public String delete(Integer id) {
        viagemRepository.delete(id);
        return "Sucesso";
    }
}
