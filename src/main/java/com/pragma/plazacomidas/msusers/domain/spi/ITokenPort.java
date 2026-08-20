package com.pragma.plazacomidas.msusers.domain.spi;

public interface ITokenPort {

    String generateToken(Long id, String email, String role, Long restaurantId);
    
}
