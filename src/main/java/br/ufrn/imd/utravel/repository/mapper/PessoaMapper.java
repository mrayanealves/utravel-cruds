package br.ufrn.imd.utravel.repository.mapper;

import br.ufrn.imd.utravel.model.Pessoa;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PessoaMapper implements RowMapper<Pessoa> {
    @Override
    public Pessoa mapRow(ResultSet resultSet, int i) throws SQLException {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(resultSet.getLong("id"));
        pessoa.setCpf(resultSet.getString("cpf"));
        pessoa.setNome(resultSet.getString("nome"));

        return pessoa;
    }
}
