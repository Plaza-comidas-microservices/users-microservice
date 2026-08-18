package com.pragma.plazacomidas.msusers.domain.api;

import java.util.List;

import com.pragma.plazacomidas.msusers.domain.model.UserModel;


public interface IUserServicePort {
    
    UserModel createOwner(UserModel ownerModel);
    
    List<UserModel> getAllOwners();

    UserModel getOwnerById(Long ownerId);
}
