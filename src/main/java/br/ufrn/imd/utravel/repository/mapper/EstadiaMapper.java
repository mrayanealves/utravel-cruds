package br.ufrn.imd.utravel.repository.mapper;

import br.ufrn.imd.utravel.model.Estadia;
import br.ufrn.imd.utravel.model.Localizacao;
import br.ufrn.imd.utravel.model.Viagem;
import br.ufrn.imd.utravel.model.ViagemDestino;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EstadiaMapper implements RowMapper<Estadia> {
    @Override
    public Estadia mapRow(ResultSet resultSet, int i) throws SQLException {
        Estadia estadia = new Estadia();
        estadia.setId(resultSet.getInt("es.id"));
        estadia.setCodigo(resultSet.getString("es.codigo"));
        estadia.setEndereco(resultSet.getString("es.endereco"));
        estadia.setTipo(resultSet.getString("es.tipo"));
        estadia.setQuantidadeQuartos(resultSet.getInt("quantidade_quartos"));
        estadia.setValorPago(resultSet.getFloat("es.valor_pago"));
        estadia.setViagemDestino(new ViagemDestino(resultSet.getInt("vd.id"),
                                                   new Viagem(resultSet.getInt("v.id"),
                                                   resultSet.getString("v.titulo"),
                                                   resultSet.getDate("v.data_inicio").toLocalDate(),
                                                   resultSet.getDate("v.data_fim").toLocalDate()),
                                                   new Localizacao(resultSet.getInt("l.id"),
                                                   resultSet.getString("l.pais"),
                                                   resultSet.getString("l.estado"),
                                                   resultSet.getString("l.cidade"))));
        return estadia;
    }
}