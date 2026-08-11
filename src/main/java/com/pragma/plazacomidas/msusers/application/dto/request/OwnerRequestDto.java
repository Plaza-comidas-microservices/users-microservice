package com.pragma.plazacomidas.msusers.application.dto.request;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OwnerRequestDto {
    private String name;
    private String lastName;
    private String cc;
    private String phone;
    private LocalDate birthDate;
    private String email;
    private String password;
}
