package br.ufrn.imd.utravel.repository;

import br.ufrn.imd.utravel.model.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class PessoaRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PessoaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Pessoa salvar(Pessoa pessoa) {
        String sql = "INSERT INTO utravel.pessoa (cpf, nome) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pessoa.getCpf());
            ps.setString(2, pessoa.getNome());
            return ps;
        }, keyHolder);
        pessoa.setId(keyHolder.getKey().longValue());
        return pessoa;
    }

    public void atualizar(Pessoa pessoa) {
        String sql = "UPDATE utravel.pessoa SET cpf = ?, nome = ? WHERE id = ?";
        jdbcTemplate.update(sql, pessoa.getCpf(), pessoa.getNome(), pessoa.getId());
    }
}
