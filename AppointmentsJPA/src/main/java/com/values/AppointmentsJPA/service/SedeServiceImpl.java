package com.values.AppointmentsJPA.service;

import com.values.AppointmentsJPA.model.Sede;
import com.values.AppointmentsJPA.model.User;
import com.values.AppointmentsJPA.repository.SedeRepository;
import com.values.AppointmentsJPA.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            Sede sede = new Sede();
            sede.setNomeSede(nome);
            sede.setPhone(phone);
            sedeRepository.save(sede);
        } catch (DataAccessException e) {
            logger.error("Error inserting Sede with name: {} and phone: {}", nome, phone, e);
        }
    }

    @Override
    public List<Sede> findAll() {
        try {
            return sedeRepository.findAll();
        } catch (DataAccessException e) {
            logger.error("Error retrieving all Sede records", e);
            return null;
        }
    }

    @Override
    @Transactional
    public void updateSede(Sede sede){
        try {
            Sede existingSede = sedeRepository.findById(sede.getSedeId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Sede Id:" + sede.getSedeId()));
            existingSede.setNomeSede(sede.getNomeSede());
            existingSede.setPhone(sede.getPhone());
            sedeRepository.save(existingSede);
        } catch (Exception e) {
            logger.error("Error updating Sede with ID: {}", sede.getSedeId(), e);
        }
    }

    @Override
    public void deleteSede(Long id){
        try{
            sedeRepository.deleteById(id);
        }catch (DataAccessException e){
            logger.error("Error while deleting record with id: {}", id, e);
        }
    }
}
