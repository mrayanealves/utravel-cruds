package br.ufrn.imd.utravel.service;

import br.ufrn.imd.utravel.model.Usuario;
import br.ufrn.imd.utravel.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepository.buscarTodos();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.buscarPorId(id);
    }

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.salvar(usuario);
    }

    public void atualizar(Usuario usuario) {
        usuarioRepository.atualizar(usuario);
    }

    public void deletar(Long id) {
        usuarioRepository.deletar(id);
    }
}
