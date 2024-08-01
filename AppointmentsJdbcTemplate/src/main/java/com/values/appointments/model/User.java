package com.values.appointments.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    public long userId;
    public String email;
    public String phone;
    public String password;
}
