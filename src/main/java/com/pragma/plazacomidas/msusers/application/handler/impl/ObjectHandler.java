package com.pragma.plazacomidas.msusers.application.handler.impl;

import com.pragma.plazacomidas.msusers.application.dto.request.ObjectRequestDto;
import com.pragma.plazacomidas.msusers.application.dto.response.ObjectResponseDto;
import com.pragma.plazacomidas.msusers.application.handler.IObjectHandler;
import com.pragma.plazacomidas.msusers.application.mapper.IObjectRequestMapper;
import com.pragma.plazacomidas.msusers.application.mapper.IObjectResponseMapper;
import com.pragma.plazacomidas.msusers.domain.api.IObjectServicePort;
import com.pragma.plazacomidas.msusers.domain.model.ObjectModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ObjectHandler implements IObjectHandler {

    private final IObjectServicePort objectServicePort;
    private final IObjectRequestMapper objectRequestMapper;
    private final IObjectResponseMapper objectResponseMapper;

    @Override
    public void saveObject(ObjectRequestDto objectRequestDto) {
        ObjectModel objectModel = objectRequestMapper.toObject(objectRequestDto);
        objectServicePort.saveObject(objectModel);
    }

    @Override
    public List<ObjectResponseDto> getAllObjects() {
        return objectResponseMapper.toResponseList(objectServicePort.getAllObjects());
    }
}