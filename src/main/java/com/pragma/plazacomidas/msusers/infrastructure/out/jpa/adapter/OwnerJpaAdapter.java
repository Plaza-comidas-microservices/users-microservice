package com.pragma.plazacomidas.msusers.infrastructure.out.jpa.adapter;

import com.pragma.plazacomidas.msusers.domain.model.OwnerModel;
import com.pragma.plazacomidas.msusers.domain.spi.IUserPersistencePort;
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
    
}
