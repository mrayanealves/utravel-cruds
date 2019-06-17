package br.ufrn.imd.utravel.controller;

import br.ufrn.imd.utravel.model.Passeio;
import br.ufrn.imd.utravel.service.PasseioService;
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
@RequestMapping("passeios")
@Api(value = "Gerenciar passeios")
public class PasseioController implements GenericController<Passeio> {
    private final PasseioService passeioService;

    @Autowired
    public PasseioController(PasseioService passeioService) {
        this.passeioService = passeioService;
    }

    @Override
    public ResponseEntity<List<Passeio>> findAll() {
        return ResponseEntity.ok(passeioService.findAll());
    }

    @Override
    public ResponseEntity<Passeio> findById(@PathVariable Integer id) {
        Optional<Passeio> passeio = passeioService.findById(id);

        if (!passeio.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(passeio.get());
    }

    @Override
    public ResponseEntity<Passeio> save(@RequestBody Passeio passeio) {
        return ResponseEntity.ok(passeioService.save(passeio));
    }

    @Override
    public ResponseEntity<Passeio> update(@PathVariable Integer id, @RequestBody Passeio passeio) {
        return ResponseEntity.ok(passeioService.update(id, passeio));
    }

    @Override
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(passeioService.delete(id));
    }
}
