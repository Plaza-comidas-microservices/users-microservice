package com.pragma.plazacomidas.msusers.infrastructure.out.jpa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.pragma.plazacomidas.msusers.domain.model.OwnerModel;
import com.pragma.plazacomidas.msusers.infrastructure.out.jpa.entity.OwnerEntity;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IOwnerEntityMapper {

    OwnerEntity toEntity(OwnerModel ownerModel);
    OwnerModel toOwnerModel(OwnerEntity ownerEntity);
    List<OwnerModel> toOwnerModelList(List<OwnerEntity> ownerEntityList);
}