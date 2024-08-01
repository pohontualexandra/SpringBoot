package com.values.AppointmentsJPA.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="sede")
public class Sede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sede_id")
    private long sedeId;

    @Column(name="nome_sede", nullable = false)
    @NotNull(message="Inserisci il nome della sede")
    private String nomeSede;

    @Column(name="phone", nullable = false)
    @NotNull(message="Inserisci il numero di telefono")
    private String phone;

    @OneToMany(mappedBy = "sede", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prenotazione> prenotazioni;

    public Sede(String nomeSede, String phone) {
        this.nomeSede = nomeSede;
        this.phone = phone;
    }

    public Sede(long sedeId, String nomeSede, String phone) {
        this.sedeId = sedeId;
        this.nomeSede = nomeSede;
        this.phone = phone;
    }
}
