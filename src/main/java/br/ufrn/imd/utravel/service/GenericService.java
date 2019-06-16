package br.ufrn.imd.utravel.service;

import br.ufrn.imd.utravel.model.AbstractModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GenericService <T extends AbstractModel> {
    List<T> findAll();

    ResponseEntity<T> findById(Integer id);

    T save(T modelo);

    T update(Integer id, T modelo);

    String delete(Integer id);
}
