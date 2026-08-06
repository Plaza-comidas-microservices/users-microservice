package com.pragma.plazacomidas.msusers.application.handler;

import com.pragma.plazacomidas.msusers.application.dto.request.ObjectRequestDto;
import com.pragma.plazacomidas.msusers.application.dto.response.ObjectResponseDto;

import java.util.List;

public interface IObjectHandler {

    void saveObject(ObjectRequestDto objectRequestDto);

    List<ObjectResponseDto> getAllObjects();
}