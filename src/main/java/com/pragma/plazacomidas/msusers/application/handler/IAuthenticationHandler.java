package com.pragma.plazacomidas.msusers.application.handler;

import com.pragma.plazacomidas.msusers.application.dto.request.LoginRequestDto;
import com.pragma.plazacomidas.msusers.application.dto.response.LoginResponseDto;

public interface IAuthenticationHandler {
    LoginResponseDto login(LoginRequestDto loginRequestDto);
}
