package br.ufrn.imd.utravel.repository.mapper;

import br.ufrn.imd.utravel.model.Empresa;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpresaMapper implements RowMapper<Empresa> {
    @Override
    public Empresa mapRow(ResultSet resultSet, int i) throws SQLException {
        Empresa empresa = new Empresa();
        empresa.setId(resultSet.getInt("e.id"));
        empresa.setCnpj(resultSet.getString("e.cnpj"));
        empresa.setNome(resultSet.getString("e.nome"));
        return empresa;
    }
}
