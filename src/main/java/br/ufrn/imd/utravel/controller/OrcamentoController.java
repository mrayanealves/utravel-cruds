package br.ufrn.imd.utravel.controller;

import br.ufrn.imd.utravel.model.Orcamento;
import br.ufrn.imd.utravel.service.OrcamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("orcamentos")
public class OrcamentoController implements GenericController<Orcamento> {
    private final OrcamentoService orcamentoService;

    public OrcamentoController(OrcamentoService orcamentoService) {
        this.orcamentoService = orcamentoService;
    }

    @Override
    public ResponseEntity<List<Orcamento>> findAll() {
        return ResponseEntity.ok(orcamentoService.findAll());
    }

    @Override
    public ResponseEntity<Orcamento> findById(Integer id) {
        Optional<Orcamento> orcamento = orcamentoService.findById(id);

        if (!orcamento.isPresent()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(orcamento.get());
    }

    @Override
    public ResponseEntity<Orcamento> save(Orcamento orcamento) {
        return ResponseEntity.ok(orcamentoService.save(orcamento));
    }

    @Override
    public ResponseEntity<Orcamento> update(Integer id, Orcamento orcamento) {
        return ResponseEntity.ok(orcamentoService.update(id, orcamento));
    }

    @Override
    public ResponseEntity<String> delete(Integer id) {
        return ResponseEntity.ok(orcamentoService.delete(id));
    }
}
