package br.ufrn.imd.utravel.repository;

import br.ufrn.imd.utravel.model.Usuario;
import br.ufrn.imd.utravel.repository.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepository implements GenericRepository<Usuario>{
    private final JdbcTemplate jdbcTemplateObject;

    private final PessoaRepository pessoaRepository;

    @Autowired
    public UsuarioRepository(DataSource dataSource, PessoaRepository pessoaRepository) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public List<Usuario> findAll() {
        String SQL = "SELECT * FROM utravel.usuario u JOIN utravel.pessoa p ON u.pessoa_id = p.id";
        List<Usuario> usuarios = jdbcTemplateObject.query(SQL, new UsuarioMapper());

        return usuarios;
    }

    @Override
    public Optional<Usuario> findById(Integer id) {
        String SQL = "SELECT * FROM utravel.usuario u JOIN utravel.pessoa p ON u.pessoa_id = p.id WHERE u.id = ?";
        List<Usuario> usuarios = jdbcTemplateObject.query(SQL, new Object[]{ id }, new UsuarioMapper());

        if (usuarios.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(usuarios.get(0));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Usuario save(Usuario usuario) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        usuario.setPessoa(pessoaRepository.save(usuario.getPessoa()));

        String SQL = "INSERT INTO utravel.usuario (pessoa_id, telefone, email, senha) VALUES (?, ?, ?, ?)";
        jdbcTemplateObject.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, usuario.getPessoa().getId());
            preparedStatement.setString(2, usuario.getTelefone());
            preparedStatement.setString(3, usuario.getEmail());
            preparedStatement.setString(4, usuario.getSenha());
            return preparedStatement;
        }, keyHolder);

        if (keyHolder.getKey() != null){
            usuario.setId(keyHolder.getKey().intValue());
        }

        return usuario;
    }

    @Override
    public Usuario update(Usuario usuario) {
        String SQL = "UPDATE utravel.usuario SET telefone = ?, email = ?, senha = ? WHERE id = ?";
        jdbcTemplateObject.update(SQL, usuario.getTelefone(), usuario.getEmail(), usuario.getSenha(), usuario.getId());

        return usuario;
    }

    @Override
    public String delete(Integer id) {
        String SQL = "DELETE FROM utravel.usuario WHERE id = ?";
        jdbcTemplateObject.update(SQL, id);

        return "Sucesso";
    }
}
