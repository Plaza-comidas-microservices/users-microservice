package com.pragma.plazacomidas.msusers.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.pragma.plazacomidas.msusers.domain.spi.IPasswordEncoderPort; // aquí estoy importantado el dominio
import com.pragma.plazacomidas.msusers.domain.usecase.UserUseCase;
import com.pragma.plazacomidas.msusers.infrastructure.out.jpa.adapter.OwnerJpaAdapter;
import com.pragma.plazacomidas.msusers.infrastructure.out.jpa.mapper.IOwnerEntityMapper;
import com.pragma.plazacomidas.msusers.infrastructure.out.jpa.repository.IOwnerRepository;
import com.pragma.plazacomidas.msusers.infrastructure.out.security.BCryptPasswordEncoderAdapter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    @Bean
    public IPasswordEncoderPort passwordEncoderPort() {
        return new BCryptPasswordEncoderAdapter(new BCryptPasswordEncoder());
    }

    @Bean
    public OwnerJpaAdapter ownerJpaAdapter(IOwnerRepository ownerRepository, IOwnerEntityMapper ownerEntityMapper) {
        return new OwnerJpaAdapter(ownerRepository, ownerEntityMapper);
    }

    @Bean
    public UserUseCase userUseCase(OwnerJpaAdapter ownerJpaAdapter) {
        return new UserUseCase(ownerJpaAdapter, passwordEncoderPort());
    }


}