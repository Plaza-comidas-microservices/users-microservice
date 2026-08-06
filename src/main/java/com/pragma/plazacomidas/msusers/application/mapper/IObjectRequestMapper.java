package com.pragma.plazacomidas.msusers.application.mapper;

import com.pragma.plazacomidas.msusers.application.dto.request.ObjectRequestDto;
import com.pragma.plazacomidas.msusers.domain.model.ObjectModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IObjectRequestMapper {
    ObjectModel toObject(ObjectRequestDto objectRequestDto);
}
