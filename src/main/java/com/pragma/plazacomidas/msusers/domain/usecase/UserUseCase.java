package com.pragma.plazacomidas.msusers.domain.usecase;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import com.pragma.plazacomidas.msusers.domain.api.IUserServicePort;
import com.pragma.plazacomidas.msusers.domain.exception.DomainException;
import com.pragma.plazacomidas.msusers.domain.model.UserModel;
import com.pragma.plazacomidas.msusers.domain.spi.IUserPersistencePort;

import com.pragma.plazacomidas.msusers.domain.spi.IPasswordEncoderPort;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final IPasswordEncoderPort passwordEncoderPort;

    public UserUseCase(IUserPersistencePort userPersistencePort, IPasswordEncoderPort passwordEncoderPort) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public UserModel createOwner(UserModel ownerModel) {
        String email = ownerModel.getEmail();
        String phoneNumber = ownerModel.getPhone();
        String cc = ownerModel.getCc();
        LocalDate birthDate = ownerModel.getBirthDate();


        if (email == null || !email.contains("@")) {
            throw new DomainException("El correo electrónico no es válido");
        }else if (phoneNumber == null || !phoneNumber.matches("^\\+?\\d{10,13}$")) {
            throw new DomainException("El número de teléfono no es válido. Ejemplo +573005698325");
        } else if (cc == null || !cc.matches("\\d+")  ) {
            throw new DomainException("El número de cédula debe ser solo dígitos");
        }else if(birthDate == null || birthDate.isAfter(LocalDate.now())){
            throw new DomainException("No puedes nacer en el futuro");
        } else if (isAdult(birthDate) == false){
            throw new DomainException("El propietario debe ser mayor de edad");
        }else{
            ownerModel.setRole("ROLE_OWNER");
            ownerModel.setPassword(passwordEncoderPort.encode(ownerModel.getPassword()));
            return userPersistencePort.saveUser(ownerModel);
        }

    }


    //Este es un método aux para ver si es mayor de edad
    public boolean isAdult(LocalDate birthDate) {
        boolean isAdult = false;
        if (birthDate != null) {
            LocalDate currentDate = LocalDate.now();
            Period age = Period.between(birthDate, currentDate);
            isAdult = age.getYears() >= 18;
        }
        return isAdult;
    }

    @Override
    public List<UserModel> getAllOwners() {
        return userPersistencePort.getAllUsers();
    }

    @Override
    public UserModel getOwnerById(Long ownerId) {
        return userPersistencePort.getUserById(ownerId);
    }
    
}
