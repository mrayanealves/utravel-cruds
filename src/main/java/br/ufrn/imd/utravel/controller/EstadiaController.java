package br.ufrn.imd.utravel.controller;

import br.ufrn.imd.utravel.model.Estadia;
import br.ufrn.imd.utravel.service.EstadiaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("estadias")
public class EstadiaController implements GenericController<Estadia> {
    private final EstadiaService estadiaService;

    public EstadiaController(EstadiaService estadiaService) {
        this.estadiaService = estadiaService;
    }

    @Override
    public ResponseEntity<List<Estadia>> findAll() {
        return ResponseEntity.ok(estadiaService.findAll());
    }

    @Override
    public ResponseEntity<Estadia> findById(Integer id) {
        Optional<Estadia> estadia = estadiaService.findById(id);

        if (!estadia.isPresent()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(estadia.get());
    }

    @Override
    public ResponseEntity<Estadia> save(Estadia estadia) {
        return ResponseEntity.ok(estadiaService.save(estadia));
    }

    @Override
    public ResponseEntity<Estadia> update(Integer id, Estadia estadia) {
        return ResponseEntity.ok(estadiaService.update(id, estadia));
    }

    @Override
    public ResponseEntity<String> delete(Integer id) {
        return ResponseEntity.ok(estadiaService.delete(id));
    }
}
