package com.pragma.plazacomidas.msusers.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.pragma.plazacomidas.msusers.application.dto.request.ClientRequestDto;
import com.pragma.plazacomidas.msusers.domain.model.UserModel;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IClientRequestMapper {
    UserModel toClient(ClientRequestDto clientRequestDto);
}
