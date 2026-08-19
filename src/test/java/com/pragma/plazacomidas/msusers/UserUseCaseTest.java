package com.pragma.plazacomidas.msusers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pragma.plazacomidas.msusers.domain.exception.DomainException;
import com.pragma.plazacomidas.msusers.domain.model.UserModel;
import com.pragma.plazacomidas.msusers.domain.spi.IPasswordEncoderPort;
import com.pragma.plazacomidas.msusers.domain.spi.IUserPersistencePort;
import com.pragma.plazacomidas.msusers.domain.spi.IRestaurantValidationPort;
import com.pragma.plazacomidas.msusers.domain.usecase.UserUseCase;


@ExtendWith(MockitoExtension.class)
public class UserUseCaseTest {


    //----------- SET UP ----------------
    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IPasswordEncoderPort passwordEncoderPort;

    @Mock
    private IRestaurantValidationPort restaurantValidationPort;

    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        userUseCase = new UserUseCase(userPersistencePort, passwordEncoderPort, restaurantValidationPort);
    }

    // ---------------------------------

    // ---------- HAPPY TESTS ----------

    @Test
    void createOwnerTest() {
        UserModel ownerModel = new UserModel(null, "Daron", "Mercado", "111234567", "+573005698325", LocalDate.of(1990,5,20), "daron@gmail.com", "12345", null, null);
        UserModel savedOwner = new UserModel(1L, "Daron", "Mercado", "111234567", "+573005698325", LocalDate.of(1990,5,20), "daron@gmail.com", "12345", "ROLE_OWNER", null);

        // Esto solo programa el comportamiento, no la encriptación real, eso ya lo hace el Spring security, yo pruebo es que se guarde la clave cifrada correctamente
        when(passwordEncoderPort.encode("12345")).thenReturn("encoded12345"); 
        
        when(userPersistencePort.saveUser(any(UserModel.class))).thenReturn(savedOwner);

        UserModel result = userUseCase.createOwner(ownerModel);
        
        assertEquals(savedOwner, result);
        assertEquals(1L, result.getId());
        assertEquals("ROLE_OWNER", result.getRole());
        verify(passwordEncoderPort, times(1)).encode("12345");
        verify(userPersistencePort, times(1)).saveUser(any(UserModel.class));

    }
    
    // ---------- SAD TESTS ------------
    
    @Test
    void emailInvalidTest(){
        UserModel ownerModel = new UserModel(null, "Daron", "Mercado", "111234567", "+573005698325", LocalDate.of(1990,5,20), "daronQgmail.com", "12344", null, null);
        DomainException exception = assertThrows(DomainException.class, 
            () -> userUseCase.createOwner(ownerModel));

        assertEquals("El correo electrónico no es válido", exception.getMessage());

        verify(userPersistencePort, never()).saveUser(any());
    }

    @Test
    void phoneInvalidTest(){
        UserModel ownerModel = new UserModel(null, "Daron", "Mercado", "111234567", "30056983", LocalDate.of(1990,5,20), "daron@gmail.com", "12345", null, null);
        DomainException exception = assertThrows(DomainException.class, 
            () -> userUseCase.createOwner(ownerModel));

        assertEquals("El número de teléfono no es válido. Ejemplo +573005698325", exception.getMessage());

        verify(userPersistencePort, never()).saveUser(any());
    }

    @Test
    void ccInvalidTest(){
        UserModel ownerModel = new UserModel(null, "Daron", "Mercado", "11123456s", "3005698325", LocalDate.of(1990,5,20), "daron@gmail.com", "12345", null, null);
        DomainException exception = assertThrows(DomainException.class, 
            () -> userUseCase.createOwner(ownerModel));

        assertEquals("El número de cédula debe ser solo dígitos", exception.getMessage());

        verify(userPersistencePort, never()).saveUser(any());
    }

    @Test
    void dateOfBirthInvalidTest(){
        UserModel ownerModel = new UserModel(null, "Daron", "Mercado", "111234567", "3005698325", LocalDate.of(LocalDate.now().getYear() + 1, 1, 1), "daron@gmail.com", "12345", null, null);
        DomainException exception = assertThrows(DomainException.class, 
            () -> userUseCase.createOwner(ownerModel));

        assertEquals("No puedes nacer en el futuro", exception.getMessage());

        verify(userPersistencePort, never()).saveUser(any());
    }

    @Test
    void ownerNotAdultTest(){
        UserModel ownerModel = new UserModel(null, "Daron", "Mercado", "111234567", "3005698325", LocalDate.of(LocalDate.now().getYear() - 10, 1, 1), "daron@gmail.com", "12345", null, null);
        DomainException exception = assertThrows(DomainException.class, 
            () -> userUseCase.createOwner(ownerModel));

        assertEquals("El propietario debe ser mayor de edad", exception.getMessage());

        verify(userPersistencePort, never()).saveUser(any());
    }

    @Test
    void createClientTest() {
        UserModel clientModel = new UserModel();
        clientModel.setName("Ingrid");
        clientModel.setLastName("Castillo");
        clientModel.setCc("111122233");
        clientModel.setPhone("+573043538372");
        clientModel.setEmail("ingrid@gmail.com");
        clientModel.setPassword("12345");

        UserModel savedClient = new UserModel();
        savedClient.setId(1L);
        savedClient.setRole("ROLE_CLIENT");

        when(passwordEncoderPort.encode("12345")).thenReturn("encoded12345");
        when(userPersistencePort.saveUser(any(UserModel.class))).thenReturn(savedClient);

        UserModel result = userUseCase.createClient(clientModel);

        assertEquals(1L, result.getId());
        assertEquals("ROLE_CLIENT", result.getRole());
        verify(passwordEncoderPort, times(1)).encode("12345");
        verify(userPersistencePort, times(1)).saveUser(any(UserModel.class));
    }

    @Test
    void clientEmailInvalidTest() {
        UserModel clientModel = new UserModel();
        clientModel.setName("Ingrid");
        clientModel.setLastName("Castillo");
        clientModel.setCc("111122233");
        clientModel.setPhone("+573043538372");
        clientModel.setEmail("ingridQgmail.com");
        clientModel.setPassword("12345");

        DomainException exception = assertThrows(DomainException.class,
                () -> userUseCase.createClient(clientModel));

        assertEquals("El correo electrónico no es válido", exception.getMessage());
        verify(userPersistencePort, never()).saveUser(any());
    }

    @Test
    void clientCcInvalidTest() {
        UserModel clientModel = new UserModel();
        clientModel.setName("Ingrid");
        clientModel.setLastName("Castillo");
        clientModel.setCc("11112223s");
        clientModel.setPhone("+573043538372");
        clientModel.setEmail("ingrid@gmail.com");
        clientModel.setPassword("12345");

        DomainException exception = assertThrows(DomainException.class,
                () -> userUseCase.createClient(clientModel));

        assertEquals("El número de cédula debe ser solo dígitos", exception.getMessage());
        verify(userPersistencePort, never()).saveUser(any());
    }

}
