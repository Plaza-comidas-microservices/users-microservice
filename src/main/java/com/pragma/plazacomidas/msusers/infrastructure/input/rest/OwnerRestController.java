package com.pragma.plazacomidas.msusers.infrastructure.input.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pragma.plazacomidas.msusers.application.dto.request.OwnerRequestDto;
import com.pragma.plazacomidas.msusers.application.dto.response.OwnerResponseDto;
import com.pragma.plazacomidas.msusers.application.dto.response.OwnerValidationResponseDto;
import com.pragma.plazacomidas.msusers.application.handler.IOwnerHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/owner")
@RequiredArgsConstructor
public class OwnerRestController {

    private final IOwnerHandler ownerHandler;

    @Operation(summary = "create a new owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Owner created", content = @Content),
            @ApiResponse(responseCode = "400",
            description = "Invalid owner data: email, phone or document format is invalid, or the owner is not of legal age",
            content = @Content),
            @ApiResponse(responseCode = "403",
            description = "Missing, invalid or insufficient token: only an ADMIN can create an owner",
            content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<OwnerResponseDto> saveObject(@RequestBody OwnerRequestDto ownerRequestDto) {
        OwnerResponseDto ownerResponseDto = ownerHandler.saveOwner(ownerRequestDto);
        return new ResponseEntity<>(ownerResponseDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all owners")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All owners returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OwnerResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<List<OwnerResponseDto>> getAllOwners() {
        return ResponseEntity.ok(ownerHandler.getAllOwners());
    }

    @Operation(summary ="Get Owner by id (used for validation between microservices)")
    @GetMapping("/{id}")
    public ResponseEntity<OwnerValidationResponseDto> getOwnerById(@PathVariable Long id){
        return ResponseEntity.ok(ownerHandler.getOwnerById(id));

    }
    
}
