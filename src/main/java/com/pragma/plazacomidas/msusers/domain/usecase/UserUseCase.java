package com.pragma.plazacomidas.msusers.domain.usecase;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import com.pragma.plazacomidas.msusers.domain.api.IUserServicePort;
import com.pragma.plazacomidas.msusers.domain.model.OwnerModel;
import com.pragma.plazacomidas.msusers.domain.spi.IUserPersistencePort;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public OwnerModel createOwner(OwnerModel ownerModel) {
        String email = ownerModel.getEmail();
        String phoneNumber = ownerModel.getPhone();
        String cc = ownerModel.getCc();
        Date birthDate = ownerModel.getBirthDate();


        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("El correo electrónico no es válido");
        }else if (phoneNumber == null || !phoneNumber.matches("\\+\\d{13}")) {
            throw new IllegalArgumentException("El número de teléfono no es válido. Ejemplo +573005698325");
        } else if (cc == null || !cc.matches("\\d")  ) {
            throw new IllegalArgumentException("El número de cédula debe ser solo dígitos");
        }else if(birthDate == null || birthDate.after(new Date())){
            throw new IllegalArgumentException("No puedes nacer en el futuro");
        } else if (isAdult(birthDate) == false){
            throw new IllegalArgumentException("El propietario debe ser mayor de edad");
        }else{
            ownerModel.setRole("ROLE_OWNER");
            return userPersistencePort.saveOwner(ownerModel);
        }

    }


    //Este es un método aux para ver si es mayor de edad
    public boolean isAdult(Date birthDate) {
        boolean isAdult = false;
        if (birthDate != null) {
            LocalDate birthLocalDate = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate currentDate = LocalDate.now();
            Period age = Period.between(birthLocalDate, currentDate);
            isAdult = age.getYears() >= 18;
        }
        return isAdult;
    }
    
}
