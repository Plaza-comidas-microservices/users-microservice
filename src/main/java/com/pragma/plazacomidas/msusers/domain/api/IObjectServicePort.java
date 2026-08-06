package com.pragma.plazacomidas.msusers.domain.api;

import com.pragma.plazacomidas.msusers.domain.model.ObjectModel;

import java.util.List;

public interface IObjectServicePort {

    void saveObject(ObjectModel objectModel);

    List<ObjectModel> getAllObjects();
}