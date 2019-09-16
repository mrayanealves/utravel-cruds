package br.ufrn.imd.utravel.repository;

import br.ufrn.imd.utravel.model.Orcamento;
import br.ufrn.imd.utravel.repository.mapper.OrcamentoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class OrcamentoRepository implements GenericRepository<Orcamento> {
    @Autowired
    private final JdbcTemplate jdbcTemplateObject;

    @Autowired
    public OrcamentoRepository(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Orcamento> findAll() {
        String SQL = "SELECT * FROM utravel.orcamento o JOIN utravel.viagem v ON o.viagem_id = v.id";
        List<Orcamento> orcamentos = jdbcTemplateObject.query(SQL, new OrcamentoMapper());

        return orcamentos;
    }

    @Override
    public Optional<Orcamento> findById(Integer id) {
        String SQL = "SELECT * FROM utravel.orcamento o JOIN utravel.viagem v ON o.viagem_id = v.id WHERE o.id = ?";
        List<Orcamento> orcamentos = jdbcTemplateObject.query(SQL, new Object[]{ id }, new OrcamentoMapper());

        if (orcamentos.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(orcamentos.get(0));
    }

    @Override
    public Orcamento save(Orcamento orcamento) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String SQL = "INSERT INTO utravel.orcamento (viagem_id, tipo, valor_estimado) VALUES (?, ?, ?)";
        jdbcTemplateObject.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, orcamento.getViagem().getId());
            preparedStatement.setString(2, orcamento.getTipo());
            preparedStatement.setFloat(3, orcamento.getValorEstimado());
            return preparedStatement;
        }, keyHolder);

        if (keyHolder.getKey() != null){
            orcamento.setId(keyHolder.getKey().intValue());
        }

        return orcamento;
    }

    @Override
    public Orcamento update(Orcamento orcamento) {
        String SQL = "UPDATE utravel.orcamento SET tipo = ?, valor_estimado = ? WHERE id = ?";
        jdbcTemplateObject.update(SQL, orcamento.getTipo(), orcamento.getValorEstimado(), orcamento.getId());

        return orcamento;
    }

    @Override
    public String delete(Integer id) {
        String SQL = "DELETE FROM utravel.orcamento WHERE id = ?";
        jdbcTemplateObject.update(SQL, id);

        return "Sucesso";
    }

    public List<Orcamento> findByViagemId(Integer viagemId){
        String SQL = "SELECT * FROM utravel.orcamento o JOIN utravel.viagem v ON o.viagem_id = v.id WHERE v.id = ?";
        List<Orcamento> orcamentos = jdbcTemplateObject.query(SQL, new Object[]{ viagemId }, new OrcamentoMapper());

        return orcamentos;
    }
}
