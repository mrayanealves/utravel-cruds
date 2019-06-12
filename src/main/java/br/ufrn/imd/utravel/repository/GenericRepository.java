package br.ufrn.imd.utravel.repository;

import br.ufrn.imd.utravel.model.AbstractModel;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface GenericRepository <T extends AbstractModel> {
    List<T> buscarTodos();

    Optional<T> buscarPorId(Long id);

    T salvar(T modelo);

    T atualizar(T modelo);

    ResponseEntity<T> deletar(Long id);
}
