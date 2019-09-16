package br.ufrn.imd.utravel.repository;

import br.ufrn.imd.utravel.model.ViagemReserva;
import br.ufrn.imd.utravel.repository.mapper.ViagemReservaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ViagemReservaRepository {
    @Autowired
    private final JdbcTemplate jdbcTemplateObject;

    @Autowired
    public ViagemReservaRepository(JdbcTemplate jdbcTemplateObject) {
        this.jdbcTemplateObject = jdbcTemplateObject;
    }

    public ViagemReserva findById(Integer viagemId, Integer reservaId){
        String SQL = "SELECT * FROM utravel.viagem_reserva vr, utravel.viagem v, utravel.reserva r, utravel.empresa e" +
                "WHERE vr.viagem_id = v.id AND vr.reserva_id = r.id AND r.empresa_id = e.id AND vr.viagem_id = ? AND vr.reserva_id = ?";
        List<ViagemReserva> viagemReservas = jdbcTemplateObject.query(SQL, new Object[]{ viagemId, reservaId }, new ViagemReservaMapper());

        if (viagemReservas.isEmpty()){
            return null;
        }

        return viagemReservas.get(0);
    }

    public void save(ViagemReserva viagemReserva){
        String SQL = "INSERT INTO utravel.viagem_reserva (viagem_id, reserva_id) VALUES (?, ?)";
        jdbcTemplateObject.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, viagemReserva.getViagem().getId());
            preparedStatement.setInt(2, viagemReserva.getReserva().getId());
            return preparedStatement;
        });
    }

    public String delete(Integer viagemId, Integer reservaId) {
        String SQL = "DELETE FROM utravel.viagem_reserva WHERE viagem_id = ? AND reserva_id = ?";
        jdbcTemplateObject.update(SQL, viagemId, reservaId);

        return "Sucesso";
    }

    public List<ViagemReserva> findByViagemId(Integer viagemId){
        String SQL = "SELECT * FROM utravel.viagem_reserva vr, utravel.viagem v, utravel.reserva r, utravel.restaurante rs, utravel.empresa e " +
                "WHERE vr.viagem_id = v.id AND vr.reserva_id = r.id AND r.restaurante_id = rs.id AND rs.empresa_id = e.id AND v.id = ?";
        List<ViagemReserva> viagemReservas = jdbcTemplateObject.query(SQL, new Object[]{ viagemId }, new ViagemReservaMapper());

        return viagemReservas;
    }
}
