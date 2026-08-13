package com.pragma.plazacomidas.msusers.application.handler;

import java.util.List;

import com.pragma.plazacomidas.msusers.application.dto.request.OwnerRequestDto;
import com.pragma.plazacomidas.msusers.application.dto.response.OwnerResponseDto;
import com.pragma.plazacomidas.msusers.application.dto.response.OwnerValidationResponseDto;


public interface IOwnerHandler {
    OwnerResponseDto saveOwner(OwnerRequestDto ownerRequestDto);

    List<OwnerResponseDto> getAllOwners();

    OwnerValidationResponseDto getOwnerById(Long ownerId);
    
}
