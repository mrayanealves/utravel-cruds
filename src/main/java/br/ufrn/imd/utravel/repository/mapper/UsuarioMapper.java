package br.ufrn.imd.utravel.repository.mapper;

import br.ufrn.imd.utravel.model.Pessoa;
import br.ufrn.imd.utravel.model.Usuario;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioMapper implements RowMapper<Usuario> {
    @Override
    public Usuario mapRow(ResultSet resultSet, int i) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(resultSet.getInt("u.id"));
        usuario.setEmail(resultSet.getString("u.email"));
        usuario.setSenha(resultSet.getString("u.senha"));
        usuario.setTelefone(resultSet.getString("u.telefone"));
        usuario.setPessoa(new Pessoa(resultSet.getInt("p.id"), resultSet.getString("p.cpf"),
                                     resultSet.getString("p.nome")));

        return usuario;
    }
}
