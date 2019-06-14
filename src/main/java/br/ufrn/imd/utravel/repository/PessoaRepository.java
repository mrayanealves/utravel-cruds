package br.ufrn.imd.utravel.repository;

import br.ufrn.imd.utravel.model.Pessoa;
import br.ufrn.imd.utravel.repository.mapper.PessoaMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class PessoaRepository implements GenericRepository<Pessoa>{
    private final JdbcTemplate jdbcTemplateObject;

    public PessoaRepository(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }


    @Override
    public List<Pessoa> findAll() {
        String SQL = "SELECT * FROM utravel.pessoa";
        List<Pessoa> pessoas = jdbcTemplateObject.query(SQL, new PessoaMapper());

        return pessoas;
    }

    @Override
    public Optional<Pessoa> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Pessoa save (Pessoa pessoa) {
        String sql = "INSERT INTO utravel.pessoa (cpf, nome) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplateObject.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pessoa.getCpf());
            ps.setString(2, pessoa.getNome());
            return ps;
        }, keyHolder);
        pessoa.setId(keyHolder.getKey().longValue());
        return pessoa;
    }

    @Override
    public Pessoa update(Pessoa modelo) {
        return null;
    }

    @Override
    public String delete(Long id) {
        return null;
    }
}
