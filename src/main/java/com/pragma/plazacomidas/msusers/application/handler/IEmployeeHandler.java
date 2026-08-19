package com.pragma.plazacomidas.msusers.application.handler;

import com.pragma.plazacomidas.msusers.application.dto.request.EmployeeRequestDto;
import com.pragma.plazacomidas.msusers.application.dto.response.EmployeeResponseDto;

public interface IEmployeeHandler {
    EmployeeResponseDto saveEmployee(EmployeeRequestDto employeeRequestDto, Long authenticatedUserId);
}
