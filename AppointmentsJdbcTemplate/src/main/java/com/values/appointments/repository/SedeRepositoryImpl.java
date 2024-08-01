package com.values.appointments.repository;

import com.values.appointments.model.Sede;
import com.values.appointments.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SedeRepositoryImpl implements SedeRepository {
    private static final String INSERT = "INSERT INTO sede(nome_sede, phone) VALUES(?, ?)";
    private static final String SELECT = "SELECT * FROM sede ORDER BY sede_id";
    private static final String UPDATE = "UPDATE sede SET nome_sede = ?, phone = ? WHERE sede_id = ?";
    private static final String DELETE = "DELETE FROM sede WHERE sede_id = ?";
    private static final String SELECT_ID = "SELECT user_id FROM users WHERE email = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SedeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertSede(String nome, String phone) {
        jdbcTemplate.update(INSERT, nome, phone);
    }

    @Override
    public long selectUserId(String email){
        return jdbcTemplate.query(SELECT_ID, new Object[]{email}, (rs, rowNum) ->{
            User user = new User();
            user.setUserId(rs.getLong("user_id"));
            return user;
        }).get(0).getUserId();
    }

    @Override
    public List<Sede> findAll() {
        return jdbcTemplate.query(SELECT, (rs, rowNum) -> {
            Sede sede = new Sede();
            sede.setSedeId(rs.getLong("sede_id"));
            sede.setNomeSede(rs.getString("nome_sede"));
            sede.setPhone(rs.getString("phone"));
            return sede;
        });
    }

    @Override
    public void updateSede(Sede sede){
        System.out.println(sede);
        jdbcTemplate.update(UPDATE, sede.getNomeSede(), sede.getPhone(), sede.getSedeId());
    }

    @Override
    public void deleteSede(long id){
        jdbcTemplate.update(DELETE, id);
    }
}
