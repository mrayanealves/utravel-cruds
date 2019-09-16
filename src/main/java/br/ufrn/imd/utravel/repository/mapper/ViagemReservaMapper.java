package br.ufrn.imd.utravel.repository.mapper;

import br.ufrn.imd.utravel.model.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ViagemReservaMapper implements RowMapper<ViagemReserva> {
    @Override
    public ViagemReserva mapRow(ResultSet resultSet, int i) throws SQLException {
        ViagemReserva viagemReserva = new ViagemReserva();
        viagemReserva.setViagem(new Viagem(resultSet.getInt("v.id"),
                resultSet.getString("v.titulo"),
                resultSet.getDate("v.data_inicio").toLocalDate(),
                resultSet.getDate("v.data_fim").toLocalDate()));
        viagemReserva.setReserva(new Reserva(resultSet.getInt("r.id"),
                                            resultSet.getLong("r.numero"),
                                            resultSet.getDate("r.data").toLocalDate(),
                                            resultSet.getString("r.mesa"),
                                            new Restaurante(resultSet.getInt("rs.id"),
                                                    resultSet.getString("rs.tipo"),
                                                    resultSet.getString("rs.avaliacao"),
                                                    resultSet.getString("rs.endereco"),
                                                    new Empresa(resultSet.getInt("e.id"),
                                                            resultSet.getString("e.cnpj"),
                                                            resultSet.getString("e.nome")))));
        return viagemReserva;
    }
}
