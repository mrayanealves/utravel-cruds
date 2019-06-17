package br.ufrn.imd.utravel.controller;

import br.ufrn.imd.utravel.model.Estadia;
import br.ufrn.imd.utravel.model.Orcamento;
import br.ufrn.imd.utravel.model.Viagem;
import br.ufrn.imd.utravel.model.ViagemDestino;
import br.ufrn.imd.utravel.service.ViagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        Optional<Viagem> viagem = viagemService.findById(id);

        if (!viagem.isPresent()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(viagem.get());
    }

    @Override
    public ResponseEntity<Viagem> save(Viagem viagem) {
        return ResponseEntity.ok(viagemService.save(viagem));
    }

    @Override
    public ResponseEntity<Viagem> update(Integer id, Viagem viagem) {
        return ResponseEntity.ok(viagemService.update(id, viagem));
    }

    @Override
    public ResponseEntity<String> delete(Integer id) {
        return ResponseEntity.ok(viagemService.delete(id));
    }

    @PostMapping("/{id}/adicionar-destino")
    public ResponseEntity<Viagem> adicionarDestino(@PathVariable Integer id, @RequestBody ViagemDestino viagemDestino){
        return ResponseEntity.ok(viagemService.adicionarDestino(id, viagemDestino));
    }

    @DeleteMapping("/{id}/deletar-destino")
    public ResponseEntity<Viagem> deletarDestino(@PathVariable Integer id, @RequestBody ViagemDestino viagemDestino){
        return ResponseEntity.ok(viagemService.removerDestino(id, viagemDestino));
    }

    @PostMapping("/{id}/adicionar-orcamento")
    public ResponseEntity<Viagem> adicionaOrcamento(@PathVariable Integer id, @RequestBody Orcamento orcamento){
        return ResponseEntity.ok(viagemService.adicionarOrcamento(id, orcamento));
    }

    @DeleteMapping("/{id}/deletar-orcamento")
    public ResponseEntity<Viagem> deletarOrcamento(@PathVariable Integer id, @RequestBody Orcamento orcamento){
        return ResponseEntity.ok(viagemService.removerOrcamento(id, orcamento));
    }

    @PostMapping("/{destino_id}/adicionar-estadia")
    public ResponseEntity<Viagem> adicionarEstadia(@PathVariable(value = "destino_id") Integer destinoId, @RequestBody Estadia estadia){
        return ResponseEntity.ok(viagemService.adicionarEstadia(destinoId, estadia));
    }

    @DeleteMapping("/{destino_id}/deletar-estadia")
    public ResponseEntity<Viagem> deletarEstadia(@PathVariable(value = "destino_id") Integer id, @RequestBody Estadia estadia){
        return ResponseEntity.ok(viagemService.removerEstadia(id, estadia));
    }

}
