package br.ufrn.imd.utravel.repository.mapper;

import br.ufrn.imd.utravel.model.Localizacao;
import br.ufrn.imd.utravel.model.Viagem;
import br.ufrn.imd.utravel.model.ViagemDestino;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ViagemDestinoMapper implements RowMapper<ViagemDestino> {
    @Override
    public ViagemDestino mapRow(ResultSet resultSet, int i) throws SQLException {
        ViagemDestino viagemDestino = new ViagemDestino();
        viagemDestino.setId(resultSet.getInt("vd.id"));
        viagemDestino.setViagem(new Viagem(resultSet.getInt("v.id"),
                                           resultSet.getDate("v.data_inicio").toLocalDate(),
                                           resultSet.getDate("v.data_fim").toLocalDate()));
        viagemDestino.setLocalizacao(new Localizacao(resultSet.getInt("l.id"),
                                                     resultSet.getString("l.pais"),
                                                     resultSet.getString("l.estado"),
                                                     resultSet.getString("l.cidade")));
        return viagemDestino;
    }
}
