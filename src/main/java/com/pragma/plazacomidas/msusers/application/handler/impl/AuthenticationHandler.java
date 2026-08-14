package com.pragma.plazacomidas.msusers.application.handler.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pragma.plazacomidas.msusers.application.dto.request.LoginRequestDto;
import com.pragma.plazacomidas.msusers.application.dto.response.LoginResponseDto;
import com.pragma.plazacomidas.msusers.application.handler.IAuthenticationHandler;
import com.pragma.plazacomidas.msusers.domain.api.IAuthenticationServicePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class AuthenticationHandler implements IAuthenticationHandler {

    private final IAuthenticationServicePort authenticationServicePort;

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        String token = authenticationServicePort.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        return new LoginResponseDto(token);
    }
}
