package br.ufrn.imd.utravel.service;

import br.ufrn.imd.utravel.exception.EntidadeNaoEncontradaException;
import br.ufrn.imd.utravel.model.Usuario;
import br.ufrn.imd.utravel.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements GenericService<Usuario>{
    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private final PessoaService pessoaService;

    public UsuarioService(UsuarioRepository usuarioRepository, PessoaService pessoaService) {
        this.usuarioRepository = usuarioRepository;
        this.pessoaService = pessoaService;
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public ResponseEntity<Usuario> findById(Integer id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (!usuario.isPresent()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario.get());
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Usuario update(Integer id, Usuario usuario) {
        Optional<Usuario> usuarioFind = usuarioRepository.findById(id);

        if (!usuarioFind.isPresent()){
            throw new EntidadeNaoEncontradaException("Não foi possível encontrar um usuário com este id");
        }

        usuario.setId(id);
        if (usuario.getPessoa() != null){
            usuario.setPessoa(pessoaService.update(usuario.getPessoa().getId(), usuario.getPessoa()));
        } else {
            usuario.setPessoa(usuarioFind.get().getPessoa());
        }

        if (!usuarioFind.get().getTelefone().equals(usuario.getTelefone())){
            if (usuario.getTelefone() == null){
                usuario.setTelefone(usuarioFind.get().getTelefone());
            }
        }

        if (!usuarioFind.get().getEmail().equals(usuario.getEmail())){
            if (usuario.getEmail() == null){
                usuario.setEmail(usuarioFind.get().getEmail());
            }
        }

        if (!usuarioFind.get().getSenha().equals(usuario.getSenha())){
            if (usuario.getSenha() == null){
                usuario.setSenha(usuarioFind.get().getSenha());
            }
        }

        return usuarioRepository.update(usuario);
    }

    @Override
    public String delete(Integer id) {
        return usuarioRepository.delete(id);
    }
}
