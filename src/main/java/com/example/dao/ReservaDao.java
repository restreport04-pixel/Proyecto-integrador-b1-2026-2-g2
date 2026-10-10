package com.example.dao;

import com.example.model.Reserva;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class ReservaDao {

    private final JdbcTemplate jdbcTemplate;

    public ReservaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Reserva> rowMapper = (rs, rowNum) -> new Reserva(
            rs.getLong("id"),
            rs.getLong("usuario_id"),
            rs.getLong("espacio_id"),
            rs.getDate("fecha").toLocalDate()
    );

    public List<Reserva> obtenerTodos() {
        String sql = "SELECT * FROM reserva ORDER BY id ASC";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public void guardar(Reserva reserva) {
        String sql = "INSERT INTO reserva (usuario_id, espacio_id, fecha) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, reserva.getUsuarioId(), reserva.getEspacioId(), Date.valueOf(reserva.getFecha()));
    }

    public void actualizar(Reserva reserva) {
        String sql = "UPDATE reserva SET usuario_id = ?, espacio_id = ?, fecha = ? WHERE id = ?";
        jdbcTemplate.update(sql, reserva.getUsuarioId(), reserva.getEspacioId(), Date.valueOf(reserva.getFecha()), reserva.getId());
    }

    public void eliminar(Long id) {
        String sql = "DELETE FROM reserva WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}