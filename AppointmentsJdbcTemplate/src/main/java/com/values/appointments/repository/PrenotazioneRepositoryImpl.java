package com.values.appointments.repository;

import com.values.appointments.model.Prenotazione;
import com.values.appointments.model.PrenotazioneView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public class PrenotazioneRepositoryImpl implements PrenotazioneRepository{
    private static final String INSERT = "INSERT INTO prenotazione(sede_id, user_id, data, orario) VALUES(?, ?, ?, ?)";
    private static final String SELECT = "SELECT p.*, s.nome_sede FROM Prenotazione AS p JOIN sede AS s ON p.sede_id = s.sede_id WHERE p.user_id = ? ORDER BY p.pren_id";

    private static final String DELETE = "DELETE FROM prenotazione WHERE pren_id=?";
    private static final String UPDATE = "UPDATE prenotazione SET data=?, orario=? WHERE pren_id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public PrenotazioneRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertPren(long sedeId, long userId, LocalDate data, LocalTime orario){
        jdbcTemplate.update(INSERT, sedeId, userId, data, orario);
    }

    @Override
    public void deletePren(long prenId){
        jdbcTemplate.update(DELETE, prenId);
    }

    @Override
    public void updatePren(long prenId, LocalDate data, LocalTime orario){
        jdbcTemplate.update(UPDATE, data, orario, prenId);
    }

    @Override
    public List<PrenotazioneView> findAll(long userId){
        return jdbcTemplate.query(SELECT, new Object[]{userId}, (rs, rowNum) -> {
            PrenotazioneView prenotazione = new PrenotazioneView();
            prenotazione.setPrenId(rs.getLong("pren_id"));
            prenotazione.setSedeId(rs.getLong("sede_id"));
            prenotazione.setSedeName(rs.getString("nome_sede"));
            prenotazione.setUserId(rs.getLong("user_id"));
            prenotazione.setData(LocalDate.parse(rs.getString("data")));
            prenotazione.setOrario(LocalTime.parse(rs.getString("orario")));
            return prenotazione;
        });
    }
}
