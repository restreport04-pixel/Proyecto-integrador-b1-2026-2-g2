package com.example.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.model.Usuario;

@Repository
public class UsuarioDao {

    private final JdbcTemplate jdbcTemplate;

    public UsuarioDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Usuario> rowMapper = (rs, rowNum) -> new Usuario(
            rs.getLong("id"),
            rs.getString("nombre"),
            rs.getString("email")
    );

    public List<Usuario> obtenerTodos() {
        String sql = "SELECT * FROM usuario ORDER BY id ASC";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<Usuario> buscarPorNombre(String nombre) {
        String sql = "SELECT * FROM usuario WHERE LOWER(nombre) LIKE LOWER(?) ORDER BY id ASC";
        return jdbcTemplate.query(sql, rowMapper, "%" + nombre + "%");
    }

    public void guardar(Usuario usuario) {
        String sql = "INSERT INTO usuario (nombre, email) VALUES (?, ?)";
        jdbcTemplate.update(sql, usuario.getNombre(), usuario.getEmail());
    }

    public void actualizar(Usuario usuario) {
        String sql = "UPDATE usuario SET nombre = ?, email = ? WHERE id = ?";
        jdbcTemplate.update(sql, usuario.getNombre(), usuario.getEmail(), usuario.getId());
    }

    public void eliminar(Long id) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}