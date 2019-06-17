package br.ufrn.imd.utravel.repository.mapper;

import br.ufrn.imd.utravel.model.Passeio;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PasseioMapper implements RowMapper<Passeio> {
    @Override
    public Passeio mapRow(ResultSet resultSet, int i) throws SQLException {
        Passeio passeio = new Passeio();
        passeio.setId(resultSet.getInt("p.id"));
        passeio.setTipo(resultSet.getString("p.tipo"));
        passeio.setEndereco(resultSet.getString("p.endereco"));
        passeio.setEmpresa(resultSet.getString("p.empresa"));
        passeio.setLocalizacao(new LocalizacaoMapper().mapRow(resultSet, i));
        return passeio;
    }
}
