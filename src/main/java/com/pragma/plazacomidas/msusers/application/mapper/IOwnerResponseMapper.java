package com.pragma.plazacomidas.msusers.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.pragma.plazacomidas.msusers.application.dto.response.OwnerResponseDto;
import com.pragma.plazacomidas.msusers.domain.model.OwnerModel;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE )
public interface IOwnerResponseMapper {
    OwnerResponseDto toResponse(OwnerModel ownerModel);

    List<OwnerResponseDto> toResponseList(List<OwnerModel> ownerModelList);
    
}
