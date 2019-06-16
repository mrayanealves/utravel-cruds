package br.ufrn.imd.utravel.repository;

import br.ufrn.imd.utravel.model.Empresa;
import br.ufrn.imd.utravel.repository.mapper.EmpresaMapper;
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
public class EmpresaRepository implements GenericRepository<Empresa> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmpresaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Empresa> findAll() {
        String sql = "SELECT * FROM utravel.empresa e";
        return jdbcTemplate.query(sql, new EmpresaMapper());
    }

    @Override
    public Optional<Empresa> findById(Integer id) {
        String sql = "SELECT * FROM utravel.empresa e WHERE e.id = ?";
        List<Empresa> empresas = jdbcTemplate.query(sql, new Object[]{id}, new EmpresaMapper());
        if(empresas.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(empresas.get(0));
    }

    @Override
    public Empresa save(Empresa empresa) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String SQL = "INSERT INTO utravel.empresa (cnpj, nome) VALUES (?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, empresa.getCnpj());
            preparedStatement.setString(2, empresa.getNome());
            return preparedStatement;
        }, keyHolder);

        if (keyHolder.getKey() != null){
            empresa.setId(keyHolder.getKey().intValue());
        }

        return empresa;
    }

    @Override
    public Empresa update(Empresa empresa) {
        String SQL = "UPDATE utravel.empresa SET cnpj = ?, nome = ? WHERE id = ?";
        jdbcTemplate.update(SQL, empresa.getCnpj(), empresa.getNome(), empresa.getId());
        return empresa;
    }

    @Override
    public String delete(Integer id) {
        String SQL = "DELETE FROM utravel.empresa WHERE id = ?";
        jdbcTemplate.update(SQL, id);
        return "Sucesso";
    }
}
