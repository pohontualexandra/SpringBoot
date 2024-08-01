package com.values.SistemaLavoro.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="presenze")
public class Presenza {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    LocalDate giorno;
    LocalTime ingresso;
    LocalTime uscita;
    @Column(name = "ore_lavoro")
    Double oreLavoro;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

}