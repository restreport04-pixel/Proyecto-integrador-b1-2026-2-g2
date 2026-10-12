package com.example.dao;

import com.example.model.Espacio;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EspacioDao {

    private final JdbcTemplate jdbcTemplate;

    public EspacioDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Espacio> rowMapper = (rs, rowNum) -> new Espacio(
            rs.getLong("id"),
            rs.getString("nombre"),
            rs.getInt("capacidad")
    );

    public List<Espacio> obtenerTodos() {
        String sql = "SELECT * FROM espacio ORDER BY id ASC";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public void guardar(Espacio espacio) {
        String sql = "INSERT INTO espacio (nombre, capacidad) VALUES (?, ?)";
        jdbcTemplate.update(sql, espacio.getNombre(), espacio.getCapacidad());
    }

    public void actualizar(Espacio espacio) {
        String sql = "UPDATE espacio SET nombre = ?, capacidad = ? WHERE id = ?";
        jdbcTemplate.update(sql, espacio.getNombre(), espacio.getCapacidad(), espacio.getId());
    }

    public void eliminar(Long id) {
        String sql = "DELETE FROM espacio WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}