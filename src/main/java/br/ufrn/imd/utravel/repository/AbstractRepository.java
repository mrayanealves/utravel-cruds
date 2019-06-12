package br.ufrn.imd.utravel.repository;
import br.ufrn.imd.utravel.model.AbstractModel;

import org.springframework.jdbc.core.JdbcTemplate;

public abstract class AbstractRepository <T extends AbstractModel> implements GenericRepository<T>{
    protected final JdbcTemplate jdbcTemplate;

    protected AbstractRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
