package com.pragma.plazacomidas.msusers.infrastructure.out.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pragma.plazacomidas.msusers.infrastructure.out.jpa.entity.OwnerEntity;

public interface IOwnerRepository extends JpaRepository<OwnerEntity, Long> {
    //Ya con esto tengo el crud del owner gratis. Spring Data JPA los genera en tiempo de ejecución
}
