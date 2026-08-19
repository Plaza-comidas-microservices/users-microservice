package com.pragma.plazacomidas.msusers.infrastructure.input.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pragma.plazacomidas.msusers.application.dto.request.EmployeeRequestDto;
import com.pragma.plazacomidas.msusers.application.dto.response.EmployeeResponseDto;
import com.pragma.plazacomidas.msusers.application.handler.IEmployeeHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeRestController {

    private final IEmployeeHandler employeeHandler;

    @Operation(summary = "Create a new employee account for a restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid employee data", content = @Content),
            @ApiResponse(responseCode = "403", description = "Missing, invalid or insufficient token: only the restaurant owner can create employees", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<EmployeeResponseDto> createEmployee(@RequestBody EmployeeRequestDto employeeRequestDto) {
        Long authenticatedUserId = (Long) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        EmployeeResponseDto employeeResponseDto = employeeHandler.saveEmployee(employeeRequestDto, authenticatedUserId);
        return new ResponseEntity<>(employeeResponseDto, HttpStatus.CREATED);
    }
}
