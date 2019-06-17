package br.ufrn.imd.utravel.repository;

import br.ufrn.imd.utravel.model.Passagem;
import br.ufrn.imd.utravel.repository.mapper.PassagemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class PassagemRepository implements GenericRepository<Passagem> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PassagemRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Passagem> findAll() {
        String sql = "SELECT * FROM utravel.passagem p JOIN utravel.empresa e ON p.empresa_id = e.id JOIN utravel.localizacao l ON p.origem_id = l.id JOIN utravel.localizacao d ON p.destino_id = d.id";
        return jdbcTemplate.query(sql, new PassagemMapper());
    }

    @Override
    public Optional<Passagem> findById(Integer id) {
        String sql = "SELECT * FROM utravel.passagem p JOIN utravel.empresa e ON p.empresa_id = e.id JOIN utravel.localizacao l ON p.origem_id = l.id JOIN utravel.localizacao d ON p.destino_id = d.id WHERE p.id = ?";
        List<Passagem> passagems = jdbcTemplate.query(sql, new Object[]{id}, new PassagemMapper());
        if(passagems.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(passagems.get(0));
    }

    @Override
    public Passagem save(Passagem passagem) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String SQL = "INSERT INTO utravel.passagem (data_ida, data_chegada, valor_pago, origem_id, destino_id, transporte_id, empresa_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDate(1, Date.valueOf(passagem.getDataIda()));
            preparedStatement.setDate(2, Date.valueOf(passagem.getDataChegada()));
            preparedStatement.setFloat(3, passagem.getValorPago());
            preparedStatement.setInt(4, passagem.getOrigem().getId());
            preparedStatement.setInt(5, passagem.getDestino().getId());
            preparedStatement.setInt(6, passagem.getTransporte().getId());
            preparedStatement.setInt(7, passagem.getEmpresa().getId());

            return preparedStatement;
        }, keyHolder);

        if (keyHolder.getKey() != null){
            passagem.setId(keyHolder.getKey().intValue());
        }

        return passagem;
    }

    @Override
    public Passagem update(Passagem passagem) {
        String SQL = "UPDATE utravel.passagem SET data_ida = ?, data_chegada = ?, valor_pago = ?, origem_id = ?, destino_id = ?, transporte_id = ?, empresa_id = ? WHERE id = ?";
        jdbcTemplate.update(SQL,
                passagem.getDataIda(),
                passagem.getDataChegada(),
                passagem.getValorPago(),
                passagem.getOrigem().getId(),
                passagem.getDestino().getId(),
                passagem.getTransporte().getId(),
                passagem.getEmpresa().getId(),
                passagem.getId());
        return passagem;
    }

    @Override
    public String delete(Integer id) {
        String SQL = "DELETE FROM utravel.passagem WHERE id = ?";
        jdbcTemplate.update(SQL, id);
        return "Sucesso";
    }
}
