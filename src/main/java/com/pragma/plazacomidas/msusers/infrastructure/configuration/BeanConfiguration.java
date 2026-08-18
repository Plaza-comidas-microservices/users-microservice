package com.pragma.plazacomidas.msusers.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.pragma.plazacomidas.msusers.domain.spi.IPasswordEncoderPort; // aquí estoy importantado el dominio
import com.pragma.plazacomidas.msusers.domain.usecase.AuthenticationUseCase;
import com.pragma.plazacomidas.msusers.domain.usecase.UserUseCase;
import com.pragma.plazacomidas.msusers.infrastructure.out.jpa.adapter.UserJpaAdapter;
import com.pragma.plazacomidas.msusers.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.plazacomidas.msusers.infrastructure.out.jpa.repository.IUserRepository;
import com.pragma.plazacomidas.msusers.infrastructure.out.security.BCryptPasswordEncoderAdapter;
import com.pragma.plazacomidas.msusers.infrastructure.out.security.JwtTokenAdapter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    @Bean
    public IPasswordEncoderPort passwordEncoderPort() {
        return new BCryptPasswordEncoderAdapter(new BCryptPasswordEncoder());
    }

    @Bean
    public UserJpaAdapter ownerJpaAdapter(IUserRepository ownerRepository, IUserEntityMapper ownerEntityMapper) {
        return new UserJpaAdapter(ownerRepository, ownerEntityMapper);
    }

    @Bean
    public UserUseCase userUseCase(UserJpaAdapter ownerJpaAdapter) {
        return new UserUseCase(ownerJpaAdapter, passwordEncoderPort());
    }

    @Bean
    public JwtTokenAdapter jwtTokenAdapter(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") long expirationTimeInMillis){
        return new JwtTokenAdapter(secret, expirationTimeInMillis);
    }
    
    @Bean
    public AuthenticationUseCase authenticationUseCase(UserJpaAdapter ownerJpaAdapter, JwtTokenAdapter jwtTokenAdapter){
        return new AuthenticationUseCase(ownerJpaAdapter, passwordEncoderPort(), jwtTokenAdapter);

    }

}