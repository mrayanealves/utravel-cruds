package br.ufrn.imd.utravel.repository.mapper;

import br.ufrn.imd.utravel.model.Viagem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ViagemMapper implements RowMapper<Viagem> {
    @Override
    public Viagem mapRow(ResultSet resultSet, int i) throws SQLException {
        Viagem viagem = new Viagem();
        viagem.setId(resultSet.getInt("v.id"));
        viagem.setTitulo(resultSet.getString("v.titulo"));
        viagem.setDataInicio(resultSet.getDate("v.data_inicio").toLocalDate());
        viagem.setDataFim(resultSet.getDate("v.data_fim").toLocalDate());
        return viagem;
    }
}
