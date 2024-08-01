package com.values.appointments.service;

import com.values.appointments.model.Sede;
import com.values.appointments.repository.SedeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

@Service
public class SedeServiceImpl implements SedeService {

    private static final Logger logger = LoggerFactory.getLogger(SedeServiceImpl.class);

    private final SedeRepository sedeRepository;

    @Autowired
    public SedeServiceImpl(SedeRepository sedeRepository) {
        this.sedeRepository = sedeRepository;
    }

    @Override
    public void insertSede(String nome, String phone) {
        try {
            sedeRepository.insertSede(nome, phone);
        } catch (DataAccessException e) {
            logger.error("Error inserting Sede with name: {} and phone: {}", nome, phone, e);
        }
    }

    @Override
    public List<Sede> findAll() {
        try {
            return sedeRepository.findAll();
        } catch (DataAccessException | SQLException e) {
            logger.error("Error retrieving all Sede records", e);
            return null;
        }
    }

    @Override
    public void updateSede(Sede sede){
        try{
            sedeRepository.updateSede(sede);
        }catch (DataAccessException e){
            logger.error("Error while updating record with name: {}", sede.nomeSede, e);
        }
    }

    @Override
    public void deleteSede(Long id){
        try{
            sedeRepository.deleteSede(id);
        }catch (DataAccessException e){
            logger.error("Error while deleting record with id: {}", id, e);
        }
    }

    @Override
    public long selectUserId(String email){
        try{
            return sedeRepository.selectUserId(email);
        }catch (DataAccessException e){
            logger.error("Error while searching for user record with email: {}", email, e);
            return 0;
        }
    }
}
