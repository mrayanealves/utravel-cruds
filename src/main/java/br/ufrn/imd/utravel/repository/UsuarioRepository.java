package br.ufrn.imd.utravel.repository;

import br.ufrn.imd.utravel.model.Pessoa;
import br.ufrn.imd.utravel.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository {
    private final JdbcTemplate jdbcTemplate;
    private final PessoaRepository pessoaRepository;

    public UsuarioRepository(JdbcTemplate jdbcTemplate, PessoaRepository pessoaRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.pessoaRepository = pessoaRepository;
    }

//    @Override
//    public List<Usuario> buscarTodos() {
//        String sql = "SELECT * FROM utravel.usuario u JOIN utravel.pessoa p ON u.pessoa_id = p.id";
//        return jdbcTemplate.query(sql,
//                (rs, rowNum) -> new Usuario(
//                        rs.getLong("u.id"),
//                        rs.getString("u.email"),
//                        rs.getString("u.senha"),
//                        rs.getString("u.telefone"),
//                        new Pessoa(
//                                rs.getLong("p.id"),
//                                rs.getString("p.cpf"),
//                                rs.getString("p.nome")
//                        )
//                )
//        );
//    }
//
//    @Override
//    public Optional<Usuario> buscarPorId(Long id) {
//        String sql = "SELECT * FROM utravel.usuario u JOIN utravel.pessoa p ON u.pessoa_id = p.id WHERE u.id = (?)";
//        Optional<Usuario> usuario = Optional.of(getJdbcTemplate().query(sql, new Object[]{id},
//                                                (rs, rowNum) -> new Usuario(
//                                                        rs.getLong("u.id"),
//                                                        rs.getString("u.email"),
//                                                        rs.getString("u.senha"),
//                                                        rs.getString("u.telefone"),
//                                                        new Pessoa(
//                                                                rs.getLong("p.id"),
//                                                                rs.getString("p.cpf"),
//                                                                rs.getString("nome")
//                                                        )
//                                                )).get(0));
//        return usuario;
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public Usuario salvar(Usuario usuario) {
//        usuario.setPessoa(pessoaRepository.salvar(usuario.getPessoa()));
//        String sql = "INSERT INTO utravel.usuario (pessoa_id, telefone, email, senha) VALUES (?, ?, ?, ?)";
//
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(connection -> {
//            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            ps.setLong(1, usuario.getPessoa().getId());
//            ps.setString(2, usuario.getTelefone());
//            ps.setString(3, usuario.getEmail());
//            ps.setString(4, usuario.getSenha());
//            return ps;
//        }, keyHolder);
//        usuario.setId(keyHolder.getKey().longValue());
//        return usuario;
//    }
//
//    @Override
//    public Usuario atualizar(Usuario usuario) {
//        jdbcTemplate.update("UPDATE utravel.usuario SET telefone = ?, email = ?, senha = ? WHERE id = ?", usuario.getTelefone(), usuario.getEmail(), usuario.getSenha(), usuario.getId());
//        return null;
//    }
//
//    public ResponseEntity deletar(Long id) {
//        jdbcTemplate.update("DELETE FROM utravel.usuario WHERE id = ?", id);
//        return null;
//    }
}
