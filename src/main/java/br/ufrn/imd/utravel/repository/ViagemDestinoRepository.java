package br.ufrn.imd.utravel.repository;

import br.ufrn.imd.utravel.model.ViagemDestino;
import br.ufrn.imd.utravel.repository.mapper.ViagemDestinoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ViagemDestinoRepository {
    @Autowired
    private final JdbcTemplate jdbcTemplateObject;

    @Autowired
    public ViagemDestinoRepository(JdbcTemplate jdbcTemplateObject) {
        this.jdbcTemplateObject = jdbcTemplateObject;
    }

    public void save(ViagemDestino viagemDestino){
        String SQL = "INSERT INTO utravel.viagem_destino (viagem_id, destino_id) VALUES (?, ?)";
        jdbcTemplateObject.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, viagemDestino.getViagem().getId());
            preparedStatement.setInt(2, viagemDestino.getLocalizacao().getId());
            return preparedStatement;
        });
    }

    public String delete(Integer id) {
        String SQL = "DELETE FROM utravel.viagem_destino WHERE id = ?";
        jdbcTemplateObject.update(SQL, id);

        return "Sucesso";
    }

    public List<ViagemDestino> findByViagemId(Integer viagemId){
        String SQL = "SELECT * FROM utravel.viagem_destino vd, utravel.viagem v, utravel.localizacao l " +
                        "WHERE vd.viagem_id = v.id AND vd.destino_id = l.id AND v.id = ?";
        List<ViagemDestino> viagemDestinos = jdbcTemplateObject.query(SQL, new Object[]{ viagemId }, new ViagemDestinoMapper());

        return viagemDestinos;
    }
}
