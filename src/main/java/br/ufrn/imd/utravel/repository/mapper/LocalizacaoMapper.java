package br.ufrn.imd.utravel.repository.mapper;

import br.ufrn.imd.utravel.model.Localizacao;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocalizacaoMapper implements RowMapper<Localizacao> {
    @Override
    public Localizacao mapRow(ResultSet resultSet, int i) throws SQLException {
        Localizacao localizacao = new Localizacao();
        localizacao.setId(resultSet.getInt("l.id"));
        localizacao.setCidade(resultSet.getString("l.cidade"));
        localizacao.setEstado(resultSet.getString("l.estado"));
        localizacao.setPais(resultSet.getString("l.pais"));

        return localizacao;
    }
}
