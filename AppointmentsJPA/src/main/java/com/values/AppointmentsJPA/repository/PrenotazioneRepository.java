package com.values.AppointmentsJPA.repository;

import com.values.AppointmentsJPA.model.Prenotazione;
import com.values.AppointmentsJPA.model.PrenotazioneView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    @Query("SELECT new com.values.AppointmentsJPA.model.PrenotazioneView(p.prenId, p.sedeId, s.nomeSede, p.userId, p.data, p.orario) " +
            "FROM Prenotazione p JOIN Sede s ON p.sedeId = s.sedeId " +
            "WHERE p.userId = :userId ORDER BY p.prenId")
    List<PrenotazioneView> findAllByUserId(@Param("userId") long userId);
}
