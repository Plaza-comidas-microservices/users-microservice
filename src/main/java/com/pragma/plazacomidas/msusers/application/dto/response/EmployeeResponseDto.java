package com.pragma.plazacomidas.msusers.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponseDto {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String role;
    private Long restaurantId;
}
