package br.ufrn.imd.utravel.repository;

import br.ufrn.imd.utravel.model.Reserva;
import br.ufrn.imd.utravel.repository.mapper.ReservaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservaRepository implements GenericRepository<Reserva> {
    @Autowired
    private final JdbcTemplate jdbcTemplateObject;

    @Autowired
    public ReservaRepository(JdbcTemplate jdbcTemplateObject) {
        this.jdbcTemplateObject = jdbcTemplateObject;
    }

    @Override
    public List<Reserva> findAll() {
        String SQL = "SELECT * FROM utravel.reserva r, utravel.restaurante rs, utravel.empresa e " +
                "WHERE r.restaurante_id = rs.id AND rs.empresa_id = e.id";
        List<Reserva> reservas = jdbcTemplateObject.query(SQL, new ReservaMapper());

        return reservas;
    }

    @Override
    public Optional<Reserva> findById(Integer id) {
        String SQL = "SELECT * FROM utravel.reserva r, utravel.restaurante rs, utravel.empresa e " +
                "WHERE r.restaurante_id = rs.id AND rs.empresa_id = e.id AND r.id = ?";
        List<Reserva> reservas = jdbcTemplateObject.query(SQL, new Object[]{id}, new ReservaMapper());

        if (reservas.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(reservas.get(0));
    }

    @Override
    public Reserva save(Reserva reserva) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String SQL = "INSERT INTO utravel.reserva (numero, data, mesa, restaurante_id) VALUES (?, ?, ?, ?)";

        jdbcTemplateObject.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, reserva.getNumero());
            preparedStatement.setDate(2, Date.valueOf(reserva.getData()));
            preparedStatement.setString(3, reserva.getMesa());
            preparedStatement.setInt(4, reserva.getRestaurante().getId());
            return preparedStatement;
        }, keyHolder);

        if (keyHolder.getKey() != null){
            reserva.setId(keyHolder.getKey().intValue());
        }

        return reserva;
    }

    @Override
    public Reserva update(Reserva reserva) {
        String SQL = "UPDATE utravel.reserva SET data = ?, mesa = ? WHERE id = ?";
        jdbcTemplateObject.update(SQL, reserva.getData(), reserva.getMesa(), reserva.getId());

        return reserva;
    }

    @Override
    public String delete(Integer id) {
        String SQL = "DELETE FROM utravel.reserva WHERE id = ?";
        jdbcTemplateObject.update(SQL, id);
        return "Sucesso";
    }
}
