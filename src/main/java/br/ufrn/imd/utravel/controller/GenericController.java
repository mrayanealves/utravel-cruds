package br.ufrn.imd.utravel.controller;

import br.ufrn.imd.utravel.model.AbstractModel;
import br.ufrn.imd.utravel.model.Pessoa;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface GenericController <T extends AbstractModel> {
    @GetMapping
    ResponseEntity<List<T>> findAll();

    @GetMapping("/{id}")
    ResponseEntity<T> findById(@PathVariable Integer id);

    @PostMapping
    ResponseEntity<T> save(@RequestBody Pessoa pessoa);

    @PutMapping("/{id}")
    ResponseEntity<T> update(@PathVariable Integer id, @RequestBody Pessoa pessoa);

    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable Integer id);

}
