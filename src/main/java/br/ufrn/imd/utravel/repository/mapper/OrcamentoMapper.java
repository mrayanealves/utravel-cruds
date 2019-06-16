package br.ufrn.imd.utravel.repository.mapper;

import br.ufrn.imd.utravel.model.Orcamento;
import br.ufrn.imd.utravel.model.Viagem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrcamentoMapper implements RowMapper<Orcamento> {
    @Override
    public Orcamento mapRow(ResultSet resultSet, int i) throws SQLException {
        Orcamento orcamento = new Orcamento();
        orcamento.setId(resultSet.getInt("o.id"));
        orcamento.setTipo(resultSet.getString("o.tipo"));
        orcamento.setValorEstimado(resultSet.getFloat("o.valor_estimado"));
        orcamento.setViagem(new Viagem(resultSet.getInt("v.id"),
                                       resultSet.getDate("v.data_inicio").toLocalDate(),
                                       resultSet.getDate("v.data_fim").toLocalDate()));

        return orcamento;
    }
}
