package br.ufrn.imd.utravel.controller;

import br.ufrn.imd.utravel.model.Localizacao;
import br.ufrn.imd.utravel.service.LocalizacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("localizacoes")
public class LocalizacaoController implements GenericController<Localizacao> {
    private final LocalizacaoService localizacaoService;

    public LocalizacaoController(LocalizacaoService localizacaoService) {
        this.localizacaoService = localizacaoService;
    }

    @Override
    public ResponseEntity<List<Localizacao>> findAll() {
        return ResponseEntity.ok(localizacaoService.findAll());
    }

    @Override
    public ResponseEntity<Localizacao> findById(Integer id) {
        Optional<Localizacao> localizacao = localizacaoService.findById(id);

        if (!localizacao.isPresent()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(localizacao.get());
    }

    @Override
    public ResponseEntity<Localizacao> save(Localizacao localizacao) {
        return ResponseEntity.ok(localizacaoService.save(localizacao));
    }

    @Override
    public ResponseEntity<Localizacao> update(Integer id, Localizacao localizacao) {
        return ResponseEntity.ok(localizacaoService.update(id, localizacao));
    }

    @Override
    public ResponseEntity<String> delete(Integer id) {
        return ResponseEntity.ok(localizacaoService.delete(id));
    }
}
