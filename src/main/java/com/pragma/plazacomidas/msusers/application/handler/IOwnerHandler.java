package com.pragma.plazacomidas.msusers.application.handler;

import com.pragma.plazacomidas.msusers.application.dto.request.OwnerRequestDto;
import com.pragma.plazacomidas.msusers.application.dto.response.OwnerResponseDto;


public interface IOwnerHandler {
    OwnerResponseDto saveOwner(OwnerRequestDto ownerRequestDto);

    
}
