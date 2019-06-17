package br.ufrn.imd.utravel.controller;

import br.ufrn.imd.utravel.model.Transporte;
import br.ufrn.imd.utravel.service.TransporteService;
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
@RequestMapping("transportes")
@Api(value = "Gerenciar transportes")
public class TransporteController implements GenericController<Transporte> {
    private final TransporteService transporteService;

    @Autowired
    public TransporteController(TransporteService transporteService) {
        this.transporteService = transporteService;
    }

    @Override
    public ResponseEntity<List<Transporte>> findAll() {
        return ResponseEntity.ok(transporteService.findAll());
    }

    @Override
    public ResponseEntity<Transporte> findById(@PathVariable Integer id) {
        Optional<Transporte> transporte = transporteService.findById(id);

        if (!transporte.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(transporte.get());
    }

    @Override
    public ResponseEntity<Transporte> save(@RequestBody Transporte transporte) {
        return ResponseEntity.ok(transporteService.save(transporte));
    }

    @Override
    public ResponseEntity<Transporte> update(@PathVariable Integer id, @RequestBody Transporte transporte) {
        return ResponseEntity.ok(transporteService.update(id, transporte));
    }

    @Override
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(transporteService.delete(id));
    }
}
