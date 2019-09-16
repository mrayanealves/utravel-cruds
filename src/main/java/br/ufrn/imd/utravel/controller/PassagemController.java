package br.ufrn.imd.utravel.controller;

import br.ufrn.imd.utravel.model.Passagem;
import br.ufrn.imd.utravel.service.PassagemService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("passagems")
@Api(value = "Gerenciar passagems")
public class PassagemController implements GenericController<Passagem> {
    private final PassagemService passagemService;

    @Autowired
    public PassagemController(PassagemService passagemService) {
        this.passagemService = passagemService;
    }

    @Override
    public ResponseEntity<List<Passagem>> findAll() {
        return ResponseEntity.ok(passagemService.findAll());
    }

    @Override
    public ResponseEntity<Passagem> findById(@PathVariable Integer id) {
        Optional<Passagem> passagem = passagemService.findById(id);

        if (!passagem.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(passagem.get());
    }

    @Override
    public ResponseEntity<Passagem> save(@RequestBody Passagem passagem) {
        return ResponseEntity.ok(passagemService.save(passagem));
    }

    @Override
    public ResponseEntity<Passagem> update(@PathVariable Integer id, @RequestBody Passagem passagem) {
        return ResponseEntity.ok(passagemService.update(id, passagem));
    }

    @Override
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(passagemService.delete(id));
    }
}
