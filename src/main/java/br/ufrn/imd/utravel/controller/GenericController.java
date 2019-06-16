package br.ufrn.imd.utravel.controller;

import br.ufrn.imd.utravel.model.AbstractModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface GenericController <T extends AbstractModel> {
    @GetMapping
    ResponseEntity<List<T>> findAll();

    @GetMapping("/{id}")
    ResponseEntity<T> findById(@PathVariable Integer id);

    @PostMapping
    ResponseEntity<T> save(@RequestBody T modelo);

    @PutMapping("/{id}")
    ResponseEntity<T> update(@PathVariable Integer id, @RequestBody T modelo);

    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable Integer id);

}
