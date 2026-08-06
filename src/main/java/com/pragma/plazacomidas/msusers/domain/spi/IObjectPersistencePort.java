package com.pragma.plazacomidas.msusers.domain.spi;

import com.pragma.plazacomidas.msusers.domain.model.ObjectModel;
import java.util.List;

public interface IObjectPersistencePort {
    ObjectModel saveObject(ObjectModel objectModel);

    List<ObjectModel> getAllObjects();
}