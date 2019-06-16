package br.ufrn.imd.utravel.repository;

import br.ufrn.imd.utravel.model.Pessoa;
import br.ufrn.imd.utravel.repository.mapper.PessoaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class PessoaRepository implements GenericRepository<Pessoa>{
    @Autowired
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
    public Optional<Pessoa> findById(Integer id) {
        String SQL = "SELECT * FROM utravel.pessoa WHERE id = ?";

        List<Pessoa> pessoas = jdbcTemplateObject.query(SQL, new Object[] { id }, new PessoaMapper());

        if (pessoas.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(pessoas.get(0));
    }

    @Override
    public Pessoa save (Pessoa pessoa) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String SQL = "INSERT INTO utravel.pessoa (cpf, nome) VALUES (?, ?)";

        jdbcTemplateObject.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, pessoa.getCpf());
            preparedStatement.setString(2, pessoa.getNome());
            return preparedStatement;
        }, keyHolder);

        if (keyHolder.getKey() != null){
            pessoa.setId(keyHolder.getKey().intValue());
        }

        return pessoa;
    }

    @Override
    public Pessoa update(Pessoa pessoa) {
        String SQL = "UPDATE utravel.pessoa SET cpf = ?, nome = ? WHERE id = ?";
        jdbcTemplateObject.update(SQL, pessoa.getCpf(), pessoa.getNome(), pessoa.getId());

        return pessoa;
    }

    @Override
    public String delete(Integer id) {
        String SQL = "DELETE FROM utravel.pessoa WHERE id = ?";
        jdbcTemplateObject.update(SQL, id);

        return "Sucesso";
    }
}
