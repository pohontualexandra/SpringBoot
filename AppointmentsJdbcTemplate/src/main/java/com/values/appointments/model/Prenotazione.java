package com.values.appointments.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prenotazione {
    public long prenId;
    public long sedeId;
    public long userId;
    public LocalDate data;
    public LocalTime orario;

    public Prenotazione(long prenId, long sedeId, LocalDate date, LocalTime time) {
    }
}
