package com.values.appointments.repository;

import com.values.appointments.model.Prenotazione;
import com.values.appointments.model.PrenotazioneView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface PrenotazioneRepository {
    void insertPren(long sedeId, long userId, LocalDate data, LocalTime orario);

    void deletePren(long prenId);

    void updatePren(long prenId, LocalDate data, LocalTime orario);

    List<PrenotazioneView> findAll(long userId);
}
