package com.pragma.plazacomidas.msusers.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientResponseDto {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String role;
}
