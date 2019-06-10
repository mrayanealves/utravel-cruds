package br.ufrn.imd.utravel.controller;

import br.ufrn.imd.utravel.model.Usuario;
import br.ufrn.imd.utravel.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuarios")
@Api(value = "Gerenciar usuários")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    @ApiOperation(value = "Listar todos os usuarios", response = Usuario[].class)
    public ResponseEntity<List<Usuario>> buscarTodos() {
        return new ResponseEntity<>(usuarioService.buscarTodos(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Buscar usuário por id", response = Usuario.class)
    public ResponseEntity<Usuario> buscarPorId(@PathVariable("id") Long id) {
        return new ResponseEntity<Usuario>(usuarioService.buscarPorId(id), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Salvar usuário", response = Usuario.class)
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.salvar(usuario), HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation(value = "Atualizar usuário")
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario) {
        usuarioService.atualizar(usuario);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Deletar usuário")
    public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
        usuarioService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
