package br.ufrn.imd.utravel.repository;

import br.ufrn.imd.utravel.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UsuarioRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Usuario> buscarTodos() {
        return jdbcTemplate.query("SELECT * FROM utravel.usuario",
                (rs, rowNum) -> new Usuario(
                        rs.getLong("id"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("telefone")
                )
        );
    }

    public Usuario buscarPorId(Long id) {
        return jdbcTemplate.query("SELECT * FROM utravel.usuario WHERE id = (?)", new Object[]{id},
                (rs, rowNum) -> new Usuario(
                        rs.getLong("id"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("telefone")
                )
        ).get(0);
    }

    public Usuario salvar(Usuario usuario) {
        jdbcTemplate.update("INSERT INTO utravel.usuario VALUES (null, ?, ?, ?)", usuario.getTelefone(), usuario.getEmail(), usuario.getSenha());
        return null;
    }

    public void atualizar(Usuario usuario) {
        jdbcTemplate.update("UPDATE utravel.usuario SET telefone = ?, email = ?, senha = ? WHERE id = ?", usuario.getTelefone(), usuario.getEmail(), usuario.getSenha(), usuario.getId());
    }

    public void deletar(Long id) {
        jdbcTemplate.update("DELETE FROM utravel.usuario WHERE id = ?", id);
    }
}
