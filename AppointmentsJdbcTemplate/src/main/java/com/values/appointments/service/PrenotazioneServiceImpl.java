package com.values.appointments.service;

import com.values.appointments.model.Prenotazione;
import com.values.appointments.model.PrenotazioneView;
import com.values.appointments.repository.PrenotazioneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class PrenotazioneServiceImpl implements PrenotazioneService{
    private static final Logger logger = LoggerFactory.getLogger(PrenotazioneServiceImpl.class);

    private final PrenotazioneRepository prenotazioneRepository;

    public PrenotazioneServiceImpl(PrenotazioneRepository prenotazioneRepository) {
        this.prenotazioneRepository = prenotazioneRepository;
    }

    @Override
    public void insertPren(long sedeId, long userId, LocalDate data, LocalTime orario){
        try{
            prenotazioneRepository.insertPren(sedeId, userId, data, orario);
        }catch (DataAccessException e){
            logger.error("Error inserting Prenotazione for user: {}", userId, e);
        }
    }

    @Override
    public void updatePren(long prenId, LocalDate data, LocalTime orario){
        try{
            prenotazioneRepository.updatePren(prenId, data, orario);
        }catch (DataAccessException e){
            logger.error("Error updating Prenotazione with ID: {}", prenId, e);
        }
    }

    @Override
    public void deletePren(long prenId){
        try{
            prenotazioneRepository.deletePren(prenId);
        }catch(DataAccessException e){
            logger.error("Error deleting appointment with ID: {}", prenId, e);
        }
    }



    @Override
    public List<PrenotazioneView> findAll(long userId){
        try{
            return prenotazioneRepository.findAll(userId);
        }catch (DataAccessException e){
            logger.error("Error retrieving appointments for user: {}", userId, e);
            return null;
        }
    }
}
