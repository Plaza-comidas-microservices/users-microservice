package com.pragma.plazacomidas.msusers.application.handler;

import com.pragma.plazacomidas.msusers.application.dto.request.OwnerRequestDto;
import com.pragma.plazacomidas.msusers.application.dto.response.OwnerResponseDto;

import java.util.List;


public interface IOwnerHandler {
    OwnerResponseDto saveOwner(OwnerRequestDto ownerRequestDto);

    List<OwnerResponseDto> getAllOwners();

    
}
