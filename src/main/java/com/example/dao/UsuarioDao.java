package com.example.dao;

import com.example.model.Usuario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioDao {
    private final JdbcTemplate jdbcTemplate;

    public UsuarioDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Usuario> obtenerTodos() {
        String sql = "SELECT id, nombre, email FROM usuario";
        return jdbcTemplate.query(sql, (rs, rowNum) -> 
            new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("email"))
        );
    }

    public void guardar(Usuario usuario) {
        String sql = "INSERT INTO usuario (nombre, email) VALUES (?, ?)";
        jdbcTemplate.update(sql, usuario.getNombre(), usuario.getEmail());
    }

    public void actualizar(Usuario usuario) {
        String sql = "UPDATE usuario SET nombre = ?, email = ? WHERE id = ?";
        jdbcTemplate.update(sql, usuario.getNombre(), usuario.getEmail(), usuario.getId());
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}