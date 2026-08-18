package com.pragma.plazacomidas.msusers.domain.spi;

import java.util.List;

import com.pragma.plazacomidas.msusers.domain.model.UserModel;

public interface IUserPersistencePort {
    UserModel saveUser(UserModel ownerModel);
    List<UserModel> getAllUsers();
    UserModel getUserById(Long ownerId);
    UserModel findByEmail(String email);
    UserModel findByRole(String role);
}
