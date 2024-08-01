package com.values.appointments.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sede {
    public long sedeId;
    public String nomeSede;
    public String phone;

    public Sede(String nome, String phone) {
    }
}
