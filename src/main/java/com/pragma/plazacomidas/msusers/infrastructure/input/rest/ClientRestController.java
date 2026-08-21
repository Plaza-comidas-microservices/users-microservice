package com.pragma.plazacomidas.msusers.infrastructure.input.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pragma.plazacomidas.msusers.application.dto.request.ClientRequestDto;
import com.pragma.plazacomidas.msusers.application.dto.response.ClientContactResponseDto;
import com.pragma.plazacomidas.msusers.application.dto.response.ClientResponseDto;
import com.pragma.plazacomidas.msusers.application.handler.IClientHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class ClientRestController {

    private final IClientHandler clientHandler;

    @Operation(summary = "Register a new client account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid client data", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<ClientResponseDto> createClient(@RequestBody ClientRequestDto clientRequestDto) {
        ClientResponseDto clientResponseDto = clientHandler.saveClient(clientRequestDto);
        return new ResponseEntity<>(clientResponseDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Get client contact info by id (used for communication between microservices)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client contact returned",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientContactResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClientContactResponseDto> getClientContactById(@PathVariable Long id) {
        return ResponseEntity.ok(clientHandler.getClientContactById(id));
    }
}
