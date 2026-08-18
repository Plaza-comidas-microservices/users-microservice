package com.pragma.plazacomidas.msusers.infrastructure.out.jpa.adapter;

import java.util.List;

import com.pragma.plazacomidas.msusers.domain.model.OwnerModel;
import com.pragma.plazacomidas.msusers.domain.spi.IUserPersistencePort;
import com.pragma.plazacomidas.msusers.infrastructure.exception.NoDataFoundException;
import com.pragma.plazacomidas.msusers.infrastructure.out.jpa.entity.OwnerEntity;
import com.pragma.plazacomidas.msusers.infrastructure.out.jpa.mapper.IOwnerEntityMapper;
import com.pragma.plazacomidas.msusers.infrastructure.out.jpa.repository.IOwnerRepository;


public class OwnerJpaAdapter implements IUserPersistencePort {
    private final IOwnerRepository ownerRepository;
    private final IOwnerEntityMapper ownerEntityMapper;

    public OwnerJpaAdapter(IOwnerRepository ownerRepository, IOwnerEntityMapper ownerEntityMapper) {
        this.ownerRepository = ownerRepository;
        this.ownerEntityMapper = ownerEntityMapper;
    }

    @Override
    public OwnerModel saveOwner(OwnerModel ownerModel) {
        return ownerEntityMapper.toOwnerModel(ownerRepository.save(ownerEntityMapper.toEntity(ownerModel)));
    }

    @Override
    public List<OwnerModel> getAllOwners() {
        List<OwnerEntity> entityList = ownerRepository.findAll();
        if (entityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return ownerEntityMapper.toOwnerModelList(entityList);
    }

    @Override
    public OwnerModel getOwnerById(Long ownerId) {
        OwnerEntity ownerEntity = ownerRepository.findById(ownerId).orElseThrow(NoDataFoundException::new);
        return ownerEntityMapper.toOwnerModel(ownerEntity);
    }

    @Override
    public OwnerModel findByEmail(String email) {
        return ownerRepository.findByEmail(email).map(ownerEntityMapper::toOwnerModel).orElse(null);
    }

    @Override
    public OwnerModel findByRole(String role) {
        return ownerRepository.findByRole(role).map(ownerEntityMapper::toOwnerModel).orElse(null);
    }
    
}
