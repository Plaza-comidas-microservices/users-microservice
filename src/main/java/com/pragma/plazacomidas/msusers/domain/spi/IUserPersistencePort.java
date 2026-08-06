package com.pragma.plazacomidas.msusers.domain.spi;
import com.pragma.plazacomidas.msusers.domain.model.OwnerModel;

public interface IUserPersistencePort {
    OwnerModel saveOwner(OwnerModel ownerModel);
}
