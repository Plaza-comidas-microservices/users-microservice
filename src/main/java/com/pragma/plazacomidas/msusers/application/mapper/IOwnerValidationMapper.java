package com.pragma.plazacomidas.msusers.application.mapper;

import org.mapstruct.ReportingPolicy;

import org.mapstruct.Mapper;

import com.pragma.plazacomidas.msusers.application.dto.response.OwnerValidationResponseDto;
import com.pragma.plazacomidas.msusers.domain.model.OwnerModel;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOwnerValidationMapper {
    
    OwnerValidationResponseDto toResponse(OwnerModel ownerModel);
    
}
