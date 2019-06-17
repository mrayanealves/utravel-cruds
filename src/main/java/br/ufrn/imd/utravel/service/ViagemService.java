package br.ufrn.imd.utravel.service;

import br.ufrn.imd.utravel.exception.EntidadeNaoEncontradaException;
import br.ufrn.imd.utravel.model.Estadia;
import br.ufrn.imd.utravel.model.Orcamento;
import br.ufrn.imd.utravel.model.Viagem;
import br.ufrn.imd.utravel.model.ViagemDestino;
import br.ufrn.imd.utravel.repository.ViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ViagemService implements GenericService<Viagem> {
    private final ViagemRepository viagemRepository;

    private final OrcamentoService orcamentoService;

    private final LocalizacaoService localizacaoService;

    private final EstadiaService estadiaService;

    @Autowired
    public ViagemService(ViagemRepository viagemRepository, OrcamentoService orcamentoService, LocalizacaoService localizacaoService, EstadiaService estadiaService) {
        this.viagemRepository = viagemRepository;
        this.orcamentoService = orcamentoService;
        this.localizacaoService = localizacaoService;
        this.estadiaService = estadiaService;
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

    public Viagem adicionarDestino(Integer id, ViagemDestino viagemDestino){
        Optional<Viagem> viagemFind = viagemRepository.findById(id);

        if (!viagemFind.isPresent()){
            throw new EntidadeNaoEncontradaException("Não foi possível encontrar uma viagem com este id.");
        }

        if (viagemDestino.getLocalizacao().getId() == null){
            viagemDestino.setLocalizacao(localizacaoService.save(viagemDestino.getLocalizacao()));
        }

        viagemDestino.setViagem(viagemFind.get());

        return viagemRepository.adicionarDestino(viagemDestino);
    }

    public Viagem removerDestino(Integer id, ViagemDestino viagemDestino){
        Optional<Viagem> viagemFind = findById(id);

        if (!viagemFind.isPresent()){
            throw new EntidadeNaoEncontradaException("Não foi possível encontrar uma viagem com este id.");
        }

        viagemDestino.setViagem(viagemFind.get());

        return viagemRepository.removerDestino(viagemDestino.getId());
    }

    public Viagem adicionarOrcamento(Integer id, Orcamento orcamento){
        Optional<Viagem> viagemFind = findById(id);

        if (!viagemFind.isPresent()){
            throw new EntidadeNaoEncontradaException("Não foi possível encontrar uma viagem com este id.");
        }

        orcamento.setViagem(viagemFind.get());
        Orcamento saveOrcamento = orcamentoService.save(orcamento);

        viagemFind = findById(id);

        if (!viagemFind.isPresent()){
            throw  new EntidadeNaoEncontradaException("Erro ao obter a viagem");
        }

        return viagemFind.get();
    }

    public Viagem removerOrcamento(Integer id, Orcamento orcamento){
        Optional<Viagem> viagemFind = findById(id);

        if (!viagemFind.isPresent()){
            throw new EntidadeNaoEncontradaException("Não foi possível encontrar uma viagem com este id.");
        }

        Optional<Orcamento> orcamentoFind = orcamentoService.findById(orcamento.getId());

        if (!orcamentoFind.isPresent()){
            throw new EntidadeNaoEncontradaException("Não foi possível encontrar um orçamento com este id.");
        }

        String removerOrcamento = orcamentoService.delete(orcamentoFind.get().getId());

        viagemFind = viagemRepository.findById(id);

        if (!viagemFind.isPresent()){
            throw new EntidadeNaoEncontradaException("Erro ao encontrar uma viagem com este id.");
        }

        return viagemFind.get();
    }

    public Viagem adicionarEstadia(Integer destinoId, Estadia estadia){
        Optional<ViagemDestino> viagemDestinoFind = viagemRepository.buscarDestinoPorId(destinoId);

        if (!viagemDestinoFind.isPresent()){
            throw new EntidadeNaoEncontradaException("Não foi possível encontrar um destino com este id.");
        }

        estadia.setViagemDestino(viagemDestinoFind.get());
        estadiaService.save(estadia);

        Optional<Viagem> viagem = viagemRepository.findById(viagemDestinoFind.get().getViagem().getId());

        if (!viagem.isPresent()){
            throw new EntidadeNaoEncontradaException("Erro ao encontrar uma viagem com este id.");
        }

        return viagem.get();
    }

    public Viagem removerEstadia(Integer destinoId, Estadia estadia){
        Optional<ViagemDestino> viagemDestinoFind = viagemRepository.buscarDestinoPorId(destinoId);

        if (!viagemDestinoFind.isPresent()){
            throw new EntidadeNaoEncontradaException("Não foi possível encontrar um destino com este id.");
        }

        estadiaService.delete(estadia.getId());

        Optional<Viagem> viagem = viagemRepository.findById(viagemDestinoFind.get().getViagem().getId());

        if (!viagem.isPresent()){
            throw new EntidadeNaoEncontradaException("Erro ao encontrar uma viagem com este id.");
        }

        return viagem.get();
    }
}
