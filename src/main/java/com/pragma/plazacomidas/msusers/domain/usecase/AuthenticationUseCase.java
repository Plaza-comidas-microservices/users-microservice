package com.pragma.plazacomidas.msusers.domain.usecase;


import com.pragma.plazacomidas.msusers.domain.api.IAuthenticationServicePort;
import com.pragma.plazacomidas.msusers.domain.exception.DomainException;
import com.pragma.plazacomidas.msusers.domain.model.UserModel;
import com.pragma.plazacomidas.msusers.domain.spi.IPasswordEncoderPort;
import com.pragma.plazacomidas.msusers.domain.spi.IUserPersistencePort;
import com.pragma.plazacomidas.msusers.domain.spi.ITokenPort;

public class AuthenticationUseCase implements IAuthenticationServicePort{

    private final IUserPersistencePort userPersistencePort;
    private final IPasswordEncoderPort passwordEncoderPort;
    private final ITokenPort tokenPort;

    public AuthenticationUseCase (IUserPersistencePort userPersistencePort, IPasswordEncoderPort passwordEncoderPort, ITokenPort tokenPort){
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoderPort = passwordEncoderPort;
        this.tokenPort = tokenPort;
    }


    @Override
    public String login(String email, String rawPassword) {
        UserModel owner = userPersistencePort.findByEmail(email);

        if(owner == null || !passwordEncoderPort.matches(rawPassword, owner.getPassword())){
            throw new DomainException("Credenciales Inválidas");
        }
        
        return tokenPort.generateToken(owner.getId(), owner.getEmail(), owner.getRole(), owner.getRestaurantId());
    }
    
}
