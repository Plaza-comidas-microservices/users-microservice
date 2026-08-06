package com.pragma.plazacomidas.msusers.domain.api;

import com.pragma.plazacomidas.msusers.domain.model.OwnerModel;


public interface IUserServicePort {
    
    OwnerModel createOwner(OwnerModel ownerModel);
}
