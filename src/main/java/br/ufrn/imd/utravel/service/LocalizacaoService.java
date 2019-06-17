package br.ufrn.imd.utravel.service;

import br.ufrn.imd.utravel.exception.EntidadeNaoEncontradaException;
import br.ufrn.imd.utravel.model.Localizacao;
import br.ufrn.imd.utravel.repository.LocalizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocalizacaoService implements GenericService<Localizacao>{
    private final LocalizacaoRepository localizacaoRepository;

    @Autowired
    public LocalizacaoService(LocalizacaoRepository localizacaoRepository) {
        this.localizacaoRepository = localizacaoRepository;
    }

    @Override
    public List<Localizacao> findAll() {
        return localizacaoRepository.findAll();
    }

    @Override
    public Optional<Localizacao> findById(Integer id) {
        return localizacaoRepository.findById(id);
    }

    @Override
    public Localizacao save(Localizacao localizacao) {
        return localizacaoRepository.save(localizacao);
    }

    @Override
    public Localizacao update(Integer id, Localizacao localizacao) {
        Optional<Localizacao> localizacaoFind = localizacaoRepository.findById(id);

        if (!localizacaoFind.isPresent()){
            throw new EntidadeNaoEncontradaException("Não foi possível encontrar uma localização com este id.");
        }

        localizacao.setId(id);

        if (!localizacaoFind.get().getPais().equals(localizacao.getPais())){
            if (localizacao.getPais() == null){
                localizacao.setPais(localizacaoFind.get().getPais());
            }
        }

        if (!localizacaoFind.get().getEstado().equals(localizacao.getEstado())){
            if (localizacao.getEstado() == null){
                localizacao.setEstado(localizacaoFind.get().getEstado());
            }
        }

        if (!localizacaoFind.get().getCidade().equals(localizacao.getCidade())){
            if (localizacao.getCidade() == null){
                localizacao.setCidade(localizacaoFind.get().getCidade());
            }
        }

        return localizacaoRepository.update(localizacao);
    }

    @Override
    public String delete(Integer id) {
        return localizacaoRepository.delete(id);
    }
}
