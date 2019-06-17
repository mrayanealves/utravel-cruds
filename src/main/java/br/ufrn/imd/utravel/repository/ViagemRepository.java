package br.ufrn.imd.utravel.repository;

import br.ufrn.imd.utravel.model.Viagem;
import br.ufrn.imd.utravel.model.ViagemDestino;
import br.ufrn.imd.utravel.model.ViagemReserva;
import br.ufrn.imd.utravel.repository.mapper.ViagemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    private final ViagemReservaRepository viagemReservaRepository;

    @Autowired
    public ViagemRepository(DataSource dataSource, OrcamentoRepository orcamentoRepository, ViagemDestinoRepository viagemDestinoRepository, ViagemReservaRepository viagemReservaRepository) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
        this.orcamentoRepository = orcamentoRepository;
        this.viagemDestinoRepository = viagemDestinoRepository;
        this.viagemReservaRepository = viagemReservaRepository;
    }

    @Override
    public List<Viagem> findAll() {
        String sql = "SELECT * FROM utravel.viagem v";
        List<Viagem> viagens =  jdbcTemplateObject.query(sql, new ViagemMapper());

        if (!viagens.isEmpty()){
            for (int i = 0; i < viagens.size(); i++){
                if (orcamentoRepository.findByViagemId(viagens.get(i).getId()) != null){
                    viagens.get(i).setOrcamentos(orcamentoRepository.findByViagemId(viagens.get(i).getId()));
                }

                if (viagemDestinoRepository.findByViagemId(viagens.get(i).getId()) != null){
                    viagens.get(i).setViagemDestinos(viagemDestinoRepository.findByViagemId(viagens.get(i).getId()));
                }

                if (viagemReservaRepository.findByViagemId(viagens.get(i).getId()) != null){
                    viagens.get(i).setViagemReservas(viagemReservaRepository.findByViagemId(viagens.get(i).getId()));
                }
            }
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

        if (orcamentoRepository.findByViagemId(viagens.get(0).getId()) != null){
            viagens.get(0).setOrcamentos(orcamentoRepository.findByViagemId(viagens.get(0).getId()));
        }

        if (viagemDestinoRepository.findByViagemId(viagens.get(0).getId()) != null){
            viagens.get(0).setViagemDestinos(viagemDestinoRepository.findByViagemId(viagens.get(0).getId()));
        }

        if (viagemReservaRepository.findByViagemId(viagens.get(0).getId()) != null){
            viagens.get(0).setViagemReservas(viagemReservaRepository.findByViagemId(viagens.get(0).getId()));
        }
        return Optional.of(viagens.get(0));
    }

    @Override
    public Viagem save(Viagem viagem) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String SQL = "INSERT INTO utravel.viagem (titulo, data_inicio, data_fim) VALUES (?, ?, ?)";

        jdbcTemplateObject.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, viagem.getTitulo());
            preparedStatement.setDate(2, Date.valueOf(viagem.getDataInicio()));
            preparedStatement.setDate(3, Date.valueOf(viagem.getDataFim()));
            return preparedStatement;
        }, keyHolder);

        if (keyHolder.getKey() != null){
            viagem.setId(keyHolder.getKey().intValue());
        }

        return viagem;
    }

    @Override
    public Viagem update(Viagem viagem) {
        String SQL = "UPDATE utravel.viagem SET titulo = ?, data_inicio= ?, data_fim = ? WHERE id = ?";
        jdbcTemplateObject.update(SQL, viagem.getTitulo(), viagem.getDataInicio(), viagem.getDataFim(), viagem.getId());
        return viagem;
    }

    @Override
    public String delete(Integer id) {
        String SQL = "DELETE FROM utravel.viagem WHERE id = ?";
        jdbcTemplateObject.update(SQL, id);
        return "Sucesso";
    }

    @Transactional(rollbackFor = Exception.class)
    public Viagem adicionarDestino(ViagemDestino viagemDestino){
        viagemDestinoRepository.save(viagemDestino);
        Optional<Viagem> viagem = findById(viagemDestino.getViagem().getId());

        if (!viagem.isPresent()){
            return null;
        }

        return viagem.get();
    }

    @Transactional(rollbackFor = Exception.class)
    public Viagem removerDestino(Integer id){
        Viagem viagemFind = viagemDestinoRepository.findById(id).getViagem();
        viagemDestinoRepository.delete(id);

        Optional<Viagem> viagem = findById(viagemFind.getId());

        if (!viagem.isPresent()){
            return null;
        }

        return viagem.get();
    }

    public Optional<ViagemDestino> buscarDestinoPorId(Integer id){
        ViagemDestino viagemDestino = viagemDestinoRepository.findById(id);

        if (viagemDestino == null){
            return Optional.empty();
        }
        return Optional.of(viagemDestino);
    }

    @Transactional(rollbackFor = Exception.class)
    public Viagem adicionarReserva(ViagemReserva viagemReserva){
        viagemReservaRepository.save(viagemReserva);
        Optional<Viagem> viagem = findById(viagemReserva.getViagem().getId());

        if (!viagem.isPresent()){
            return null;
        }

        return viagem.get();
    }

    @Transactional(rollbackFor = Exception.class)
    public Viagem removerReserva(Integer viagemId, Integer reservaId){
        Viagem viagemFind = viagemDestinoRepository.findById(viagemId).getViagem();
        viagemReservaRepository.delete(viagemId, reservaId);

        Optional<Viagem> viagem = findById(viagemFind.getId());

        if (!viagem.isPresent()){
            return null;
        }

        return viagem.get();
    }
}
