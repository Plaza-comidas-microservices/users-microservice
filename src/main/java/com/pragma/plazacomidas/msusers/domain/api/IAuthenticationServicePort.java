package com.pragma.plazacomidas.msusers.domain.api;

public interface IAuthenticationServicePort {
    
    String login(String email, String rawPassword);
}
