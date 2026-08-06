package com.pragma.plazacomidas.msusers.application.handler.impl;

import com.pragma.plazacomidas.msusers.application.handler.IOwnerHandler;
import com.pragma.plazacomidas.msusers.domain.api.IUserServicePort;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pragma.plazacomidas.msusers.application.mapper.IOwnerRequestMapper;
import com.pragma.plazacomidas.msusers.application.mapper.IOwnerResponseMapper;
import com.pragma.plazacomidas.msusers.application.dto.request.OwnerRequestDto;
import com.pragma.plazacomidas.msusers.application.dto.response.OwnerResponseDto;
import com.pragma.plazacomidas.msusers.domain.model.OwnerModel; //esto está bien??



@RequiredArgsConstructor
@Transactional
@Service
public class OwnerHandler implements IOwnerHandler {

    private final IUserServicePort userServicePort;
    private final IOwnerResponseMapper ownerResponseMapper;
    private final IOwnerRequestMapper ownerRequestMapper;


   
    @Override
    public OwnerResponseDto saveOwner(OwnerRequestDto ownerRequestDto) {
        //1 mapeo de entrada
        OwnerModel ownerModel = ownerRequestMapper.toOwner(ownerRequestDto);
        //2 llamo al caso de uso
        OwnerModel createdOwner = userServicePort.createOwner(ownerModel);
        //3 mapeo de salida
        OwnerResponseDto ownerResponseDto = ownerResponseMapper.toResponse(createdOwner);

        return ownerResponseDto;
    }
    
}
