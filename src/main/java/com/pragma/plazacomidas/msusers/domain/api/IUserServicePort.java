package com.pragma.plazacomidas.msusers.domain.api;

import java.util.List;

import com.pragma.plazacomidas.msusers.domain.model.OwnerModel;


public interface IUserServicePort {
    
    OwnerModel createOwner(OwnerModel ownerModel);
    
    List<OwnerModel> getAllOwners();
}
