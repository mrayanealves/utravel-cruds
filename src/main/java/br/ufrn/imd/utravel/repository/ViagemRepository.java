package br.ufrn.imd.utravel.repository;

import br.ufrn.imd.utravel.model.Viagem;
import br.ufrn.imd.utravel.model.ViagemDestino;
import br.ufrn.imd.utravel.repository.mapper.ViagemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class ViagemRepository implements GenericRepository<Viagem> {
    @Autowired
    private final JdbcTemplate jdbcTemplateObject;

    private final OrcamentoRepository orcamentoRepository;

    private final ViagemDestinoRepository viagemDestinoRepository;

    @Autowired
    public ViagemRepository(DataSource dataSource, OrcamentoRepository orcamentoRepository, ViagemDestinoRepository viagemDestinoRepository) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
        this.orcamentoRepository = orcamentoRepository;
        this.viagemDestinoRepository = viagemDestinoRepository;
    }

    @Override
    public List<Viagem> findAll() {
        String sql = "SELECT * FROM utravel.viagem v";
        List<Viagem> viagens =  jdbcTemplateObject.query(sql, new ViagemMapper());

        for (int i = 0; i < viagens.size(); i++){
            viagens.get(i).setOrcamentos(orcamentoRepository.findByViagemId(viagens.get(0).getId()));
            viagens.get(i).setViagemDestinos(viagemDestinoRepository.findByViagemId(viagens.get(0).getId()));
        }

        return viagens;
    }

    @Override
    public Optional<Viagem> findById(Integer id) {
        String sql = "SELECT * FROM utravel.viagem v WHERE v.id = ?";
        List<Viagem> viagens = jdbcTemplateObject.query(sql, new Object[]{id}, new ViagemMapper());
        if(viagens.isEmpty()) {
            return Optional.empty();
        }

        viagens.get(0).setOrcamentos(orcamentoRepository.findByViagemId(viagens.get(0).getId()));
        viagens.get(0).setViagemDestinos(viagemDestinoRepository.findByViagemId(viagens.get(0).getId()));
        return Optional.of(viagens.get(0));
    }

    @Override
    public Viagem save(Viagem viagem) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String SQL = "INSERT INTO utravel.viagem (data_inicio, data_fim) VALUES (?, ?)";

        jdbcTemplateObject.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDate(1, Date.valueOf(viagem.getDataInicio()));
            preparedStatement.setDate(2, Date.valueOf(viagem.getDataFim()));
            return preparedStatement;
        }, keyHolder);

        if (keyHolder.getKey() != null){
            viagem.setId(keyHolder.getKey().intValue());
        }

        return viagem;
    }

    @Override
    public Viagem update(Viagem viagem) {
        String SQL = "UPDATE utravel.viagem SET data_inicio= ?, data_fim = ? WHERE id = ?";
        jdbcTemplateObject.update(SQL, viagem.getDataInicio(), viagem.getDataFim(), viagem.getId());
        return viagem;
    }

    @Override
    public String delete(Integer id) {
        String SQL = "DELETE FROM utravel.viagem WHERE id = ?";
        jdbcTemplateObject.update(SQL, id);
        return "Sucesso";
    }

    public Viagem adicionarDestino(ViagemDestino viagemDestino){
        viagemDestinoRepository.save(viagemDestino);
        Optional<Viagem> viagem = findById(viagemDestino.getViagem().getId());

        if (!viagem.isPresent()){
            return null;
        }

        return viagem.get();
    }
}
