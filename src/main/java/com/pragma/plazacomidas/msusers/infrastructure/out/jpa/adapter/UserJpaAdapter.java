package com.pragma.plazacomidas.msusers.infrastructure.out.jpa.adapter;

import java.util.List;

import com.pragma.plazacomidas.msusers.domain.model.UserModel;
import com.pragma.plazacomidas.msusers.domain.spi.IUserPersistencePort;
import com.pragma.plazacomidas.msusers.infrastructure.exception.NoDataFoundException;
import com.pragma.plazacomidas.msusers.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.plazacomidas.msusers.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.plazacomidas.msusers.infrastructure.out.jpa.repository.IUserRepository;


public class UserJpaAdapter implements IUserPersistencePort {
    private final IUserRepository ownerRepository;
    private final IUserEntityMapper ownerEntityMapper;

    public UserJpaAdapter(IUserRepository ownerRepository, IUserEntityMapper ownerEntityMapper) {
        this.ownerRepository = ownerRepository;
        this.ownerEntityMapper = ownerEntityMapper;
    }

    @Override
    public UserModel saveUser(UserModel ownerModel) {
        return ownerEntityMapper.toUserModel(ownerRepository.save(ownerEntityMapper.toEntity(ownerModel)));
    }

    @Override
    public List<UserModel> getAllUsers() {
        List<UserEntity> entityList = ownerRepository.findAll();
        if (entityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return ownerEntityMapper.toUserModelList(entityList);
    }

    @Override
    public UserModel getUserById(Long ownerId) {
        UserEntity ownerEntity = ownerRepository.findById(ownerId).orElseThrow(NoDataFoundException::new);
        return ownerEntityMapper.toUserModel(ownerEntity);
    }

    @Override
    public UserModel findByEmail(String email) {
        return ownerRepository.findByEmail(email).map(ownerEntityMapper::toUserModel).orElse(null);
    }

    @Override
    public UserModel findByRole(String role) {
        return ownerRepository.findByRole(role).map(ownerEntityMapper::toUserModel).orElse(null);
    }
    
}
