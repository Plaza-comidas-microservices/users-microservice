package com.pragma.plazacomidas.msusers.domain.spi;


public interface IPasswordEncoderPort {
    String encode(String password);
}