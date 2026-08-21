package com.pragma.plazacomidas.msusers.application.handler;

import com.pragma.plazacomidas.msusers.application.dto.request.ClientRequestDto;
import com.pragma.plazacomidas.msusers.application.dto.response.ClientContactResponseDto;
import com.pragma.plazacomidas.msusers.application.dto.response.ClientResponseDto;

public interface IClientHandler {
    ClientResponseDto saveClient(ClientRequestDto clientRequestDto);

    ClientContactResponseDto getClientContactById(Long clientId);
}
