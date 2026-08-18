package com.pragma.plazacomidas.msusers.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.pragma.plazacomidas.msusers.domain.spi.IPasswordEncoderPort; // aquí estoy importantado el dominio
import com.pragma.plazacomidas.msusers.domain.spi.IUserPersistencePort;
import com.pragma.plazacomidas.msusers.domain.usecase.AuthenticationUseCase;
import com.pragma.plazacomidas.msusers.domain.usecase.UserUseCase;
import com.pragma.plazacomidas.msusers.infrastructure.out.jpa.adapter.OwnerJpaAdapter;
import com.pragma.plazacomidas.msusers.infrastructure.out.jpa.mapper.IOwnerEntityMapper;
import com.pragma.plazacomidas.msusers.infrastructure.out.jpa.repository.IOwnerRepository;
import com.pragma.plazacomidas.msusers.infrastructure.out.security.BCryptPasswordEncoderAdapter;
import com.pragma.plazacomidas.msusers.infrastructure.out.security.JwtTokenAdapter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

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

    @Bean
    public JwtTokenAdapter jwtTokenAdapter(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") long expirationTimeInMillis){
        return new JwtTokenAdapter(secret, expirationTimeInMillis);
    }
    
    @Bean
    public AuthenticationUseCase authenticationUseCase(OwnerJpaAdapter ownerJpaAdapter, JwtTokenAdapter jwtTokenAdapter){
        return new AuthenticationUseCase(ownerJpaAdapter, passwordEncoderPort(), jwtTokenAdapter);

    }

}