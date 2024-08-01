package com.values.AppointmentsJPA.service;

import com.values.AppointmentsJPA.model.PrenotazioneView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public interface PrenotazioneService {

    void insert(long sedeId, long userId, LocalDate data, LocalTime orario);

    void update(long prenId, LocalDate data, LocalTime orario);

    void delete(long prenId);

    List<PrenotazioneView> findAll(long userId);
}
