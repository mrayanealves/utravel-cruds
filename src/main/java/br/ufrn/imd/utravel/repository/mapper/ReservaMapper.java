package br.ufrn.imd.utravel.repository.mapper;

import br.ufrn.imd.utravel.model.Empresa;
import br.ufrn.imd.utravel.model.Reserva;
import br.ufrn.imd.utravel.model.Restaurante;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservaMapper implements RowMapper<Reserva> {
    @Override
    public Reserva mapRow(ResultSet resultSet, int i) throws SQLException {
        Reserva reserva = new Reserva();
        reserva.setId(resultSet.getInt("r.id"));
        reserva.setData(resultSet.getDate("r.data").toLocalDate());
        reserva.setMesa(resultSet.getString("r.mesa"));
        reserva.setRestaurante(new Restaurante(resultSet.getInt("rs.id"),
                                               resultSet.getString("rs.tipo"),
                                               resultSet.getString("rs.avaliacao"),
                                               resultSet.getString("rs.endereco"),
                                               new Empresa(resultSet.getInt("e.id"),
                                                           resultSet.getString("e.cnpj"),
                                                           resultSet.getString("e.nome"))));
        return reserva;
    }
}
