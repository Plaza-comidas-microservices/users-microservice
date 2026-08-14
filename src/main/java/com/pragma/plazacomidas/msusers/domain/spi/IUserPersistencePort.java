package com.pragma.plazacomidas.msusers.domain.spi;

import java.util.List;

import com.pragma.plazacomidas.msusers.domain.model.OwnerModel;

public interface IUserPersistencePort {
    OwnerModel saveOwner(OwnerModel ownerModel);
    List<OwnerModel> getAllOwners();
    OwnerModel getOwnerById(Long ownerId);
    OwnerModel findByEmail(String email);
}
