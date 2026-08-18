package com.pragma.plazacomidas.msusers.domain.spi;


public interface IPasswordEncoderPort {
    String encode(String password);
    Boolean matches(String raw, String encodeed);
}