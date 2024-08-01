package com.values.AppointmentsJPA.model;

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
public class PrenotazioneView {
    public long prenId;
    public long sedeId;
    public String sedeName;
    public long userId;
    public LocalDate data;
    public LocalTime orario;
}
