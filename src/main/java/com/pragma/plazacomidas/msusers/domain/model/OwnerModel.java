package com.pragma.plazacomidas.msusers.domain.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OwnerModel {
    private Long id;
    private String name;
    private String lastName;
    private String cc;
    private String phone;
    private Date birthDate;
    private String email;
    private String password;
    private String role;

}
