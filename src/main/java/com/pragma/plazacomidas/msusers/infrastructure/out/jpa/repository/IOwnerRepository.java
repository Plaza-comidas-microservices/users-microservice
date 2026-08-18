package com.pragma.plazacomidas.msusers.infrastructure.out.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pragma.plazacomidas.msusers.infrastructure.out.jpa.entity.OwnerEntity;

public interface IOwnerRepository extends JpaRepository<OwnerEntity, Long> {
    //Ya con esto tengo el crud del owner gratis. Spring Data JPA los genera en tiempo de ejecución
    Optional<OwnerEntity> findByEmail(String email); //No viene heredado, por eso toca poner la firma, pero solo con eso ya Spring genera la consulta SQL internamente
    Optional<OwnerEntity> findByRole(String role);
}

