package com.pragma.plazacomidas.msusers.domain.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pragma.plazacomidas.msusers.domain.exception.DomainException;
import com.pragma.plazacomidas.msusers.domain.model.UserModel;
import com.pragma.plazacomidas.msusers.domain.spi.IPasswordEncoderPort;
import com.pragma.plazacomidas.msusers.domain.spi.ITokenPort;
import com.pragma.plazacomidas.msusers.domain.spi.IUserPersistencePort;

@ExtendWith(MockitoExtension.class)
class AuthenticationUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IPasswordEncoderPort passwordEncoderPort;

    @Mock
    private ITokenPort tokenPort;

    private AuthenticationUseCase authenticationUseCase;

    @BeforeEach
    void setUp() {
        authenticationUseCase = new AuthenticationUseCase(userPersistencePort, passwordEncoderPort, tokenPort);
    }

    private UserModel buildValidUser() {
        UserModel user = new UserModel();
        user.setId(1L);
        user.setEmail("admin@gmail.com");
        user.setPassword("hashedPassword");
        user.setRole("ROLE_ADMIN");
        return user;
    }

    // ---------- HAPPY PATH ----------

    @Test
    void shouldReturnTokenWhenCredentialsAreValid() {
        // Given
        UserModel user = buildValidUser();

        when(userPersistencePort.findByEmail("admin@gmail.com")).thenReturn(user);
        when(passwordEncoderPort.matches("rawPassword", "hashedPassword")).thenReturn(true);
        when(tokenPort.generateToken(1L, "admin@gmail.com", "ROLE_ADMIN")).thenReturn("fake-jwt-token");

        // When
        String token = authenticationUseCase.login("admin@gmail.com", "rawPassword");

        // Then
        assertEquals("fake-jwt-token", token);
    }

    // ---------- SAD PATHS ----------

    @Test
    void shouldThrowExceptionWhenUserDoesNotExist() {
        when(userPersistencePort.findByEmail("noexiste@gmail.com")).thenReturn(null);

        DomainException exception = assertThrows(DomainException.class,
                () -> authenticationUseCase.login("noexiste@gmail.com", "1234567890"));

        assertEquals("Credenciales Inválidas", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenPasswordDoesNotMatch() {
        UserModel user = buildValidUser();

        when(userPersistencePort.findByEmail("admin@gmail.com")).thenReturn(user);
        when(passwordEncoderPort.matches("claveIncorrecta", "hashedPassword")).thenReturn(false);

        DomainException exception = assertThrows(DomainException.class,
                () -> authenticationUseCase.login("admin@gmail.com", "claveIncorrecta"));

        assertEquals("Credenciales Inválidas", exception.getMessage());
    }

    @Test
    void shouldReturnSameMessageForBothInvalidCasesForSecurity() {
        when(userPersistencePort.findByEmail("noexiste@gmail.com")).thenReturn(null);
        DomainException notFoundException = assertThrows(DomainException.class,
                () -> authenticationUseCase.login("noexiste@gmail.com", "clave"));

        UserModel user = buildValidUser();
        when(userPersistencePort.findByEmail("admin@gmail.com")).thenReturn(user);
        when(passwordEncoderPort.matches("claveIncorrecta", "hashedPassword")).thenReturn(false);
        DomainException wrongPasswordException = assertThrows(DomainException.class,
                () -> authenticationUseCase.login("admin@gmail.com", "claveIncorrecta"));

        assertEquals(notFoundException.getMessage(), wrongPasswordException.getMessage());
    }
}
