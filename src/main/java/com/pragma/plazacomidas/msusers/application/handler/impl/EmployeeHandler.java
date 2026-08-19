package com.pragma.plazacomidas.msusers.application.handler.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pragma.plazacomidas.msusers.application.dto.request.EmployeeRequestDto;
import com.pragma.plazacomidas.msusers.application.dto.response.EmployeeResponseDto;
import com.pragma.plazacomidas.msusers.application.handler.IEmployeeHandler;
import com.pragma.plazacomidas.msusers.application.mapper.IEmployeeRequestMapper;
import com.pragma.plazacomidas.msusers.application.mapper.IEmployeeResponseMapper;
import com.pragma.plazacomidas.msusers.domain.api.IUserServicePort;
import com.pragma.plazacomidas.msusers.domain.model.UserModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class EmployeeHandler implements IEmployeeHandler {

    private final IUserServicePort userServicePort;
    private final IEmployeeRequestMapper employeeRequestMapper;
    private final IEmployeeResponseMapper employeeResponseMapper;

    @Override
    public EmployeeResponseDto saveEmployee(EmployeeRequestDto employeeRequestDto, Long authenticatedUserId) {
        UserModel employeeModel = employeeRequestMapper.toEmployee(employeeRequestDto);
        UserModel createdEmployee = userServicePort.createEmployee(employeeModel, authenticatedUserId);
        return employeeResponseMapper.toResponse(createdEmployee);
    }
}
