package com.pragma.plazacomidas.msusers.infrastructure.configuration;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pragma.plazacomidas.msusers.domain.model.OwnerModel;
import com.pragma.plazacomidas.msusers.domain.spi.IPasswordEncoderPort;
import com.pragma.plazacomidas.msusers.domain.spi.IUserPersistencePort;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class DataSeedConfiguration {

     @Bean
    public CommandLineRunner seedAdminUser(IUserPersistencePort userPersistencePort, IPasswordEncoderPort passwordEncoderPort){
        return args -> {
            if(userPersistencePort.findByRole("ROLE_ADMIN") == null ){
                OwnerModel adminUser = new OwnerModel();
                adminUser.setName("Daron");
                adminUser.setLastName("Mercado");
                adminUser.setCc("1112148306");
                adminUser.setPhone("3043538272");
                adminUser.setBirthDate(LocalDate.of(2004, 6, 11));
                adminUser.setEmail("daron@gmail.com");
                adminUser.setPassword(passwordEncoderPort.encode("Daron123."));
                adminUser.setRole("ROLE_ADMIN");
                userPersistencePort.saveOwner(adminUser);
            }
        };
    }

}
