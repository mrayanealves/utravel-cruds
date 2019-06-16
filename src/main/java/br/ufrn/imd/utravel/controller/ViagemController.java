package br.ufrn.imd.utravel.controller;

import br.ufrn.imd.utravel.model.Viagem;
import br.ufrn.imd.utravel.service.ViagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("viagens")
public class ViagemController implements GenericController<Viagem> {
    private final ViagemService viagemService;

    @Autowired
    public ViagemController(ViagemService viagemService) {
        this.viagemService = viagemService;
    }

    @Override
    public ResponseEntity<List<Viagem>> findAll() {
        return ResponseEntity.ok(viagemService.findAll());
    }

    @Override
    public ResponseEntity<Viagem> findById(Integer id) {
        return viagemService.findById(id);
    }

    @Override
    public ResponseEntity<Viagem> save(Viagem viagem) {
        return ResponseEntity.ok(viagemService.save(viagem));
    }

    @Override
    public ResponseEntity<Viagem> update(Integer id, Viagem viagem) {
        return ResponseEntity.ok(viagemService.save(viagem));
    }

    @Override
    public ResponseEntity<String> delete(Integer id) {
        return ResponseEntity.ok(viagemService.delete(id));
    }
}
