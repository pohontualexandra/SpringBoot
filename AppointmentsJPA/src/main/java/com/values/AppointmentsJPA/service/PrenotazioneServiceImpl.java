package com.values.AppointmentsJPA.service;

import com.values.AppointmentsJPA.model.Prenotazione;
import com.values.AppointmentsJPA.model.PrenotazioneView;
import com.values.AppointmentsJPA.repository.PrenotazioneRepository;
import jakarta.transaction.Transactional;
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
    @Transactional
    public void insert(long sedeId, long userId, LocalDate data, LocalTime orario){
        try{
            Prenotazione prenotazione = new Prenotazione();
            prenotazione.setSedeId(sedeId);
            prenotazione.setUserId(userId);
            prenotazione.setData(data);
            prenotazione.setOrario(orario);
            prenotazioneRepository.save(prenotazione);
        }catch (DataAccessException e){
            logger.error("Error inserting Prenotazione for user: {}", userId, e);
        }
    }

    @Override
    @Transactional
    public void update(long prenId, LocalDate data, LocalTime orario){
        try {
            Prenotazione prenotazione = prenotazioneRepository.findById(prenId).orElseThrow(() -> new IllegalArgumentException("Invalid prenotazione Id:" + prenId));
            prenotazione.setData(data);
            prenotazione.setOrario(orario);
            prenotazioneRepository.save(prenotazione);
        } catch (Exception e) {
            logger.error("Error updating Prenotazione with ID: {}", prenId, e);
        }
    }

    @Override
    @Transactional
    public void delete(long prenId){
        try{
            prenotazioneRepository.deleteById(prenId);
        }catch(DataAccessException e){
            logger.error("Error deleting appointment with ID: {}", prenId, e);
        }
    }

    @Override
    public List<PrenotazioneView> findAll(long userId){
        try{

            return prenotazioneRepository.findAllByUserId(userId);
        }catch (DataAccessException e){
            logger.error("Error retrieving appointments for user: {}", userId, e);
            return null;
        }
    }
}
