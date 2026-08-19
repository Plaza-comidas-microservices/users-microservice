package com.pragma.plazacomidas.msusers.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequestDto {
    private String name;
    private String lastName;
    private String cc;
    private String phone;
    private String email;
    private String idRol;
    private String password;
    private Long restaurantId;
}
