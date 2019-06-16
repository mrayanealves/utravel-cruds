package br.ufrn.imd.utravel.controller;

import br.ufrn.imd.utravel.model.Usuario;
import br.ufrn.imd.utravel.service.UsuarioService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuarios")
@Api(value = "Gerenciar usuários")
public class UsuarioController implements GenericController<Usuario> {
    @Autowired
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public ResponseEntity<List<Usuario>> findAll() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @Override
    public ResponseEntity<Usuario> findById(Integer id) {
        return usuarioService.findById(id);
    }

    @Override
    public ResponseEntity<Usuario> save(Usuario usuario) {
        return ResponseEntity.ok(usuarioService.save(usuario));
    }

    @Override
    public ResponseEntity<Usuario> update(Integer id, Usuario usuario) {
        return ResponseEntity.ok(usuarioService.update(id, usuario));
    }

    @Override
    public ResponseEntity<String> delete(Integer id) {
        return ResponseEntity.ok(usuarioService.delete(id));
    }
}