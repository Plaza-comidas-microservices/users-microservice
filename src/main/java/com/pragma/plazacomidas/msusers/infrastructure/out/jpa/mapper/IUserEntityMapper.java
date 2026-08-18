package com.pragma.plazacomidas.msusers.infrastructure.out.jpa.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.pragma.plazacomidas.msusers.domain.model.UserModel;
import com.pragma.plazacomidas.msusers.infrastructure.out.jpa.entity.UserEntity;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUserEntityMapper {

    UserEntity toEntity(UserModel ownerModel);
    UserModel toUserModel(UserEntity ownerEntity);
    List<UserModel> toUserModelList(List<UserEntity> ownerEntityList);
}