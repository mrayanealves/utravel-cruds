package br.ufrn.imd.utravel.service;

import br.ufrn.imd.utravel.model.AbstractModel;

import java.util.List;
import java.util.Optional;

public interface GenericService <T extends AbstractModel> {
    List<T> findAll();

    Optional<T> findById(Integer id);

    T save(T modelo);

    T update(Integer id, T modelo);

    String delete(Integer id);
}
