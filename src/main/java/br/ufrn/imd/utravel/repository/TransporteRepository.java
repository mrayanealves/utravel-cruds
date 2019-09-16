package br.ufrn.imd.utravel.repository;

import br.ufrn.imd.utravel.model.Transporte;
import br.ufrn.imd.utravel.repository.mapper.TransporteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class TransporteRepository implements GenericRepository<Transporte> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TransporteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Transporte> findAll() {
        String sql = "SELECT * FROM utravel.transporte t JOIN utravel.viagem v ON t.viagem_id = v.id";
        return jdbcTemplate.query(sql, new TransporteMapper());
    }

    @Override
    public Optional<Transporte> findById(Integer id) {
        String sql = "SELECT * FROM utravel.transporte t JOIN utravel.viagem ON t.viagem_id = viagem.id WHERE t.id = ?";
        List<Transporte> transportes = jdbcTemplate.query(sql, new Object[]{id}, new TransporteMapper());
        if(transportes.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(transportes.get(0));
    }

    @Override
    public Transporte save(Transporte transporte) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String SQL = "INSERT INTO utravel.transporte (tipo, proprio, viagem_id) VALUES (?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, transporte.getTipo());
            preparedStatement.setInt(2, transporte.getProprio());
            preparedStatement.setInt(3, transporte.getViagem().getId());
            return preparedStatement;
        }, keyHolder);

        if (keyHolder.getKey() != null){
            transporte.setId(keyHolder.getKey().intValue());
        }

        return transporte;
    }

    @Override
    public Transporte update(Transporte transporte) {
        String SQL = "UPDATE utravel.transporte SET tipo = ?, proprio = ?, viagem_id = ? WHERE id = ?";
        jdbcTemplate.update(SQL,
                transporte.getTipo(),
                transporte.getProprio(),
                transporte.getViagem().getId(),
                transporte.getId());
        return transporte;
    }

    @Override
    public String delete(Integer id) {
        String SQL = "DELETE FROM utravel.transporte WHERE id = ?";
        jdbcTemplate.update(SQL, id);
        return "Sucesso";
    }
}
