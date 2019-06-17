package br.ufrn.imd.utravel.controller;

import br.ufrn.imd.utravel.model.Reserva;
import br.ufrn.imd.utravel.service.ReservaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("reservas")
public class ReservaController implements GenericController<Reserva>{
    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @Override
    public ResponseEntity<List<Reserva>> findAll() {
        return ResponseEntity.ok(reservaService.findAll());
    }

    @Override
    public ResponseEntity<Reserva> findById(Integer id) {
        Optional<Reserva> reserva = reservaService.findById(id);

        if (!reserva.isPresent()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(reserva.get());
    }

    @Override
    public ResponseEntity<Reserva> save(Reserva reserva) {
        return ResponseEntity.ok(reservaService.save(reserva));
    }

    @Override
    public ResponseEntity<Reserva> update(Integer id, Reserva reserva) {
        return ResponseEntity.ok(reservaService.update(id, reserva));
    }

    @Override
    public ResponseEntity<String> delete(Integer id) {
        return ResponseEntity.ok(reservaService.delete(id));
    }
}
