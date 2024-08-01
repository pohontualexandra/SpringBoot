package com.values.AppointmentsJPA.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "prenotazione")
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pren_id")
    private long prenId;

    @Column(name = "sede_id", nullable = false)
    @NotNull(message = "Scegli una sede")
    private long sedeId;

    @Column(name = "user_id", nullable = false)
    @NotNull(message = "Scegli un utente")
    private long userId;

    @Column(name = "data", nullable = false)
    @NotNull(message = "Scegli una data")
    private LocalDate data;

    @Column(name = "orario", nullable = false)
    @NotNull(message = "Scegli un orario")
    private LocalTime orario;

    @ManyToOne
    @JoinColumn(name = "sede_id", insertable = false, updatable = false)
    private Sede sede;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    public Prenotazione(long prenId, long sedeId, LocalDate date, LocalTime time) {
        this.prenId = prenId;
        this.sedeId = sedeId;
        this.data = date;
        this.orario = time;
    }

    public Prenotazione(long l, LocalDate now, LocalTime now1) {
    }
}
