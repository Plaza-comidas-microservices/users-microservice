package com.pragma.plazacomidas.msusers.application.handler.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pragma.plazacomidas.msusers.application.dto.request.ClientRequestDto;
import com.pragma.plazacomidas.msusers.application.dto.response.ClientResponseDto;
import com.pragma.plazacomidas.msusers.application.handler.IClientHandler;
import com.pragma.plazacomidas.msusers.application.mapper.IClientRequestMapper;
import com.pragma.plazacomidas.msusers.application.mapper.IClientResponseMapper;
import com.pragma.plazacomidas.msusers.domain.api.IUserServicePort;
import com.pragma.plazacomidas.msusers.domain.model.UserModel;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class ClientHandler implements IClientHandler {

    private final IUserServicePort userServicePort;
    private final IClientRequestMapper clientRequestMapper;
    private final IClientResponseMapper clientResponseMapper;

    @Override
    public ClientResponseDto saveClient(ClientRequestDto clientRequestDto) {
        UserModel clientModel = clientRequestMapper.toClient(clientRequestDto);
        UserModel createdClient = userServicePort.createClient(clientModel);
        return clientResponseMapper.toResponse(createdClient);
    }
}
