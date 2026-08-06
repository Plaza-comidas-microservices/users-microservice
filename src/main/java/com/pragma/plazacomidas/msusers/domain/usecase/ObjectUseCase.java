package com.pragma.plazacomidas.msusers.domain.usecase;

import com.pragma.plazacomidas.msusers.domain.api.IObjectServicePort;
import com.pragma.plazacomidas.msusers.domain.model.ObjectModel;
import com.pragma.plazacomidas.msusers.domain.spi.IObjectPersistencePort;

import java.util.List;

public class ObjectUseCase implements IObjectServicePort {

    private final IObjectPersistencePort objectPersistencePort;

    public ObjectUseCase(IObjectPersistencePort objectPersistencePort) {
        this.objectPersistencePort = objectPersistencePort;
    }

    @Override
    public void saveObject(ObjectModel objectModel) {
        objectPersistencePort.saveObject(objectModel);
    }

    @Override
    public List<ObjectModel> getAllObjects() {
        return objectPersistencePort.getAllObjects();
    }
}