package br.ufrn.imd.utravel.repository.mapper;

import br.ufrn.imd.utravel.model.Localizacao;
import br.ufrn.imd.utravel.model.Passagem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PassagemMapper implements RowMapper<Passagem> {
    @Override
    public Passagem mapRow(ResultSet resultSet, int i) throws SQLException {
        Passagem passagem = new Passagem();
        passagem.setDataIda(resultSet.getDate("data_ida").toLocalDate());
        passagem.setDataChegada(resultSet.getDate("data_chegada").toLocalDate());
        passagem.setValorPago(resultSet.getFloat("valor_pago"));
        passagem.setOrigem(new LocalizacaoMapper().mapRow(resultSet, i));
        passagem.setDestino(((RowMapper<Localizacao>) (resultSet1, i1) -> {
            Localizacao localizacao = new Localizacao();
            localizacao.setId(resultSet.getInt("d.id"));
            localizacao.setCidade(resultSet.getString("d.cidade"));
            localizacao.setEstado(resultSet.getString("d.estado"));
            localizacao.setPais(resultSet.getString("d.pais"));
            return localizacao;
        }).mapRow(resultSet, i));
        passagem.setTransporte(new TransporteMapper().mapRow(resultSet, i));
        passagem.setEmpresa(new EmpresaMapper().mapRow(resultSet, i));
        return passagem;
    }
}
