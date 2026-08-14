package com.pragma.plazacomidas.msusers.infrastructure.out.security;

import com.pragma.plazacomidas.msusers.domain.spi.IPasswordEncoderPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class BCryptPasswordEncoderAdapter implements IPasswordEncoderPort {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public BCryptPasswordEncoderAdapter(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public String encode(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    @Override
    public Boolean matches(String raw, String encodeed) {
        return bCryptPasswordEncoder.matches(raw, encodeed);
    }
    
}

