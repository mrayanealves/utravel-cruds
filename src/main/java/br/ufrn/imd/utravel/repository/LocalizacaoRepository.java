package br.ufrn.imd.utravel.repository;

import br.ufrn.imd.utravel.model.Localizacao;
import br.ufrn.imd.utravel.repository.mapper.LocalizacaoMapper;
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
public class LocalizacaoRepository implements GenericRepository<Localizacao>{
    @Autowired
    private final JdbcTemplate jdbcTemplateObject;

    @Autowired
    public LocalizacaoRepository(JdbcTemplate jdbcTemplateObject) {
        this.jdbcTemplateObject = jdbcTemplateObject;
    }

    @Override
    public List<Localizacao> findAll() {
        String SQL = "SELECT * FROM utravel.localizacao l";
        List<Localizacao> localizacaos = jdbcTemplateObject.query(SQL, new LocalizacaoMapper());

        return localizacaos;
    }

    @Override
    public Optional<Localizacao> findById(Integer id) {
        String SQL = "SELECT * FROM utravel.localizacao l WHERE l.id = ?";

        List<Localizacao> localizacaos = jdbcTemplateObject.query(SQL, new Object[] { id }, new LocalizacaoMapper());

        if (localizacaos.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(localizacaos.get(0));
    }

    @Override
    public Localizacao save(Localizacao localizacao) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String SQL = "INSERT INTO utravel.localizacao (pais, cidade, estado) VALUES (?, ?, ?)";

        jdbcTemplateObject.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, localizacao.getPais());
            preparedStatement.setString(2, localizacao.getCidade());
            preparedStatement.setString(3, localizacao.getEstado());
            return preparedStatement;
        }, keyHolder);

        if (keyHolder.getKey() != null){
            localizacao.setId(keyHolder.getKey().intValue());
        }

        return localizacao;
    }

    @Override
    public Localizacao update(Localizacao localizacao) {
        String SQL = "UPDATE utravel.localizacao SET pais = ?, cidade = ?, estado = ? WHERE id = ?";
        jdbcTemplateObject.update(SQL, localizacao.getPais(), localizacao.getCidade(), localizacao.getEstado(),
                                  localizacao.getId());
        return localizacao;
    }

    @Override
    public String delete(Integer id) {
        String SQL = "DELETE FROM utravel.localizacao WHERE id = ?";
        jdbcTemplateObject.update(SQL, id);

        return "Sucesso";
    }
}
