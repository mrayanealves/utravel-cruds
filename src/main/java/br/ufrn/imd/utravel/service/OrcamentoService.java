package br.ufrn.imd.utravel.service;

import br.ufrn.imd.utravel.exception.EntidadeNaoEncontradaException;
import br.ufrn.imd.utravel.model.Orcamento;
import br.ufrn.imd.utravel.repository.OrcamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrcamentoService implements GenericService<Orcamento>{
    private final OrcamentoRepository orcamentoRepository;

    @Autowired
    public OrcamentoService(OrcamentoRepository orcamentoRepository) {
        this.orcamentoRepository = orcamentoRepository;
    }

    @Override
    public List<Orcamento> findAll() {
        return orcamentoRepository.findAll();
    }

    @Override
    public Optional<Orcamento> findById(Integer id) {
        return orcamentoRepository.findById(id);
    }

    @Override
    public Orcamento save(Orcamento orcamento) {
        return orcamentoRepository.save(orcamento);
    }

    @Override
    public Orcamento update(Integer id, Orcamento orcamento) {
        Optional<Orcamento> orcamentoFind = orcamentoRepository.findById(id);

        if (!orcamentoFind.isPresent()){
            throw new EntidadeNaoEncontradaException("Não foi possível encontrar um orçamento com este id.");
        }

        orcamento.setId(id);

        if (!orcamentoFind.get().getTipo().equals(orcamento.getTipo())){
            if (orcamento.getTipo() == null){
                orcamento.setTipo(orcamentoFind.get().getTipo());
            }
        }

        if (!orcamentoFind.get().getValorEstimado().equals(orcamento.getValorEstimado())){
            if (orcamento.getValorEstimado() == null){
                orcamento.setValorEstimado(orcamentoFind.get().getValorEstimado());
            }
        }

        return orcamentoRepository.update(orcamento);
    }

    @Override
    public String delete(Integer id) {
        return orcamentoRepository.delete(id);
    }
}
