package com.pragma.plazacomidas.msusers.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.pragma.plazacomidas.msusers.application.dto.response.EmployeeResponseDto;
import com.pragma.plazacomidas.msusers.domain.model.UserModel;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IEmployeeResponseMapper {
    EmployeeResponseDto toResponse(UserModel userModel);
}
