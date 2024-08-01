package com.values.AppointmentsJPA.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    public long userId;

    @Column(name="email", nullable = false, unique = true)
    @NotEmpty(message="Inserisci un e-mail valido")
    public String email;

    @Column(name="phone", nullable = false)
    @NotEmpty(message="Numero di telefono richiesto")
    public String phone;

    @Column(name="password", nullable = false)
    @NotEmpty(message="Inserisci una password")
    public String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Prenotazione> prenotazioni;
}
