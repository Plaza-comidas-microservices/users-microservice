package com.pragma.plazacomidas.msusers.infrastructure.input.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pragma.plazacomidas.msusers.application.dto.request.LoginRequestDto;
import com.pragma.plazacomidas.msusers.application.dto.response.LoginResponseDto;
import com.pragma.plazacomidas.msusers.application.handler.IAuthenticationHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationRestController {

    private final IAuthenticationHandler authenticationHandler;

    @Operation(summary = "Login with email and password, returns a JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LoginResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid credentials", content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto loginResponseDto = authenticationHandler.login(loginRequestDto);
        return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
    }
}
