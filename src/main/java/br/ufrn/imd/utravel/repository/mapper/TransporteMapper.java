package br.ufrn.imd.utravel.repository.mapper;

import br.ufrn.imd.utravel.model.Transporte;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransporteMapper implements RowMapper<Transporte> {
    @Override
    public Transporte mapRow(ResultSet resultSet, int i) throws SQLException {
        Transporte transporte = new Transporte();
        transporte.setId(resultSet.getInt("t.id"));
        transporte.setTipo(resultSet.getString("t.tipo"));
        transporte.setProprio(resultSet.getInt("t.proprio"));
        transporte.setViagem(new ViagemMapper().mapRow(resultSet, i));
        return transporte;
    }
}
