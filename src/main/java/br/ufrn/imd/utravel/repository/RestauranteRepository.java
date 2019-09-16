package br.ufrn.imd.utravel.repository;

import br.ufrn.imd.utravel.model.Restaurante;
import br.ufrn.imd.utravel.repository.mapper.RestauranteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class RestauranteRepository implements GenericRepository<Restaurante> {
    private final JdbcTemplate jdbcTemplate;

    private final EmpresaRepository empresaRepository;

    @Autowired
    public RestauranteRepository(JdbcTemplate jdbcTemplate, EmpresaRepository empresaRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.empresaRepository = empresaRepository;
    }

    @Override
    public List<Restaurante> findAll() {
        String sql = "SELECT * FROM utravel.restaurante r JOIN utravel.empresa e ON r.empresa_id = e.id";
        return jdbcTemplate.query(sql, new RestauranteMapper());
    }

    @Override
    public Optional<Restaurante> findById(Integer id) {
        String sql = "SELECT * FROM utravel.restaurante r JOIN utravel.empresa e ON r.empresa_id = e.id  WHERE r.id = ?";
        List<Restaurante> restaurantes = jdbcTemplate.query(sql, new Object[]{id}, new RestauranteMapper());
        if(restaurantes.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(restaurantes.get(0));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Restaurante save(Restaurante restaurante) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        restaurante.setEmpresa(empresaRepository.save(restaurante.getEmpresa()));
        String SQL = "INSERT INTO utravel.restaurante (empresa_id, tipo, avaliacao, endereco) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, restaurante.getEmpresa().getId());
            preparedStatement.setString(2, restaurante.getTipo());
            preparedStatement.setString(3, restaurante.getAvaliacao());
            preparedStatement.setString(4, restaurante.getEndereco());
            return preparedStatement;
        }, keyHolder);

        if (keyHolder.getKey() != null){
            restaurante.setId(keyHolder.getKey().intValue());
        }

        return restaurante;
    }

    @Override
    public Restaurante update(Restaurante restaurante) {
        String SQL = "UPDATE utravel.restaurante SET empresa_id = ?, tipo = ?, avaliacao = ?, endereco = ? WHERE id = ?";
        jdbcTemplate.update(SQL,
                restaurante.getEmpresa().getId(),
                restaurante.getTipo(),
                restaurante.getAvaliacao(),
                restaurante.getEndereco(),
                restaurante.getId());
        return restaurante;
    }

    @Override
    public String delete(Integer id) {
        String SQL = "DELETE FROM utravel.restaurante WHERE id = ?";
        jdbcTemplate.update(SQL, id);
        return "Sucesso";
    }
}
