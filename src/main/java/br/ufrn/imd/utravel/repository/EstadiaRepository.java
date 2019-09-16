package br.ufrn.imd.utravel.repository;

import br.ufrn.imd.utravel.model.Estadia;
import br.ufrn.imd.utravel.repository.mapper.EstadiaMapper;
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
public class EstadiaRepository implements GenericRepository<Estadia> {
    @Autowired
    private final JdbcTemplate jdbcTemplateObject;

    @Autowired
    public EstadiaRepository(JdbcTemplate jdbcTemplateObject) {
        this.jdbcTemplateObject = jdbcTemplateObject;
    }

    @Override
    public List<Estadia> findAll() {
        String SQL = "SELECT * FROM utravel.estadia es, utravel.viagem_destino vd, utravel.viagem v, utravel.localizacao l " +
                "WHERE es.viagem_destino_id = vd.id AND vd.viagem_id = v.id AND vd.destino_id = l.id";
        List<Estadia> estadias = jdbcTemplateObject.query(SQL, new EstadiaMapper());

        return estadias;
    }

    @Override
    public Optional<Estadia> findById(Integer id) {
        String SQL = "SELECT * FROM utravel.estadia es, utravel.viagem_destino vd, utravel.viagem v, utravel.localizacao l " +
                "WHERE es.viagem_destino_id = vd.id AND vd.viagem_id = v.id AND vd.destino_id = l.id AND es.id = ?";
        List<Estadia> estadias = jdbcTemplateObject.query(SQL, new Object[]{id}, new EstadiaMapper());

        if (estadias.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(estadias.get(0));
    }

    @Override
    public Estadia save(Estadia estadia) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String SQL = "INSERT INTO utravel.estadia (codigo, endereco, tipo, quantidade_quartos, valor_pago, viagem_destino_id) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplateObject.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, estadia.getCodigo());
            preparedStatement.setString(2, estadia.getEndereco());
            preparedStatement.setString(3, estadia.getTipo());
            preparedStatement.setInt(4, estadia.getQuantidadeQuartos());
            preparedStatement.setFloat(5, estadia.getValorPago());
            preparedStatement.setInt(6, estadia.getViagemDestino().getId());
            return preparedStatement;
        }, keyHolder);

        if (keyHolder.getKey() != null){
            estadia.setId(keyHolder.getKey().intValue());
        }

        return estadia;
    }

    @Override
    public Estadia update(Estadia estadia) {
        String SQL = "UPDATE utravel.estadia SET endereco = ?, tipo = ?, quantidade_quartos = ?, valor_pago = ? WHERE id = ?";
        jdbcTemplateObject.update(SQL, estadia.getEndereco(), estadia.getTipo(), estadia.getQuantidadeQuartos(), estadia.getValorPago(), estadia.getId());

        return estadia;
    }

    @Override
    public String delete(Integer id) {
        String SQL = "DELETE FROM utravel.estadia WHERE id = ?";
        jdbcTemplateObject.update(SQL, id);

        return "Sucesso";
    }

    public List<Estadia> findByViagemDestinoId(Integer viagemDestinoId){
        String SQL = "SELECT * FROM utravel.estadia es, utravel.viagem_destino vd, utravel.viagem v, utravel.localizacao l " +
                "WHERE es.viagem_destino_id = vd.id AND vd.viagem_id = v.id AND vd.destino_id = l.id AND es.viagem_destino_id = ?";
        List<Estadia> estadias = jdbcTemplateObject.query(SQL, new Object[]{viagemDestinoId}, new EstadiaMapper());

        return estadias;
    }
}
