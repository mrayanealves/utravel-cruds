package br.ufrn.imd.utravel.repository.mapper;

import br.ufrn.imd.utravel.model.Empresa;
import br.ufrn.imd.utravel.model.Restaurante;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RestauranteMapper implements RowMapper<Restaurante> {
    @Override
    public Restaurante mapRow(ResultSet resultSet, int i) throws SQLException {
        Restaurante restaurante = new Restaurante();
        restaurante.setId(resultSet.getInt("r.id"));
        restaurante.setTipo(resultSet.getString("r.tipo"));
        restaurante.setAvaliacao(resultSet.getString("r.avaliacao"));
        restaurante.setEndereco(resultSet.getString("r.endereco"));
        restaurante.setEmpresa(new EmpresaMapper().mapRow(resultSet, i));
        return restaurante;
    }
}
