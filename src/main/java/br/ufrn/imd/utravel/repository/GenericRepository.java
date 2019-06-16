package br.ufrn.imd.utravel.repository;

import br.ufrn.imd.utravel.model.AbstractModel;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface GenericRepository <T extends AbstractModel> {
    List<T> findAll();

    Optional<T> findById(Integer id);

    T save(T modelo);

    T update(T modelo);

    String delete(Integer id);
}
