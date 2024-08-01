package com.values.appointments.service;

import com.values.appointments.model.Prenotazione;
import com.values.appointments.model.PrenotazioneView;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public interface PrenotazioneService {

    void insertPren(long sedeId, long userId, LocalDate data, LocalTime orario);

    void updatePren(long prenId, LocalDate data, LocalTime orario);

    void deletePren(long prenId);

    List<PrenotazioneView> findAll(long userId);
}
