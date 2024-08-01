package com.values.BookStoreLoginSpring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {
    int id_utente;
    int id_prodotto;
    int quantita;
    double prezzo;
}
