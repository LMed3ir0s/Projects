package med3ir0s.userService.controller;

import med3ir0s.userService.dto.*;
import med3ir0s.userService.exception.ApiError;
import med3ir0s.userService.exception.GlobalExceptionHandler;
import med3ir0s.userService.exception.InvalidCredentialException;
import med3ir0s.userService.exception.UsernameAlreadyExistsException;
import med3ir0s.userService.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes do AuthController")
class AuthControllerTest {

    @Mock
    private AuthService authService;

    @Mock
    private WebRequest webRequest;

    @InjectMocks
    private AuthController authController;

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    // -------------------------------
    // TESTES DE REGISTRO
    // -------------------------------

    @Test
    @DisplayName("Deve registrar um novo usuário com sucesso")
    void deveRegistrarUsuarioComSucesso() {
        // Arrange
        RegisterRequest request = new RegisterRequest();
        request.setUsername("Lucas");
        request.setEmail("lucas@email.com");
        request.setPassword("123456");

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setUsername("Lucas");
        userDTO.setEmail("lucas@email.com");
        userDTO.setRoles(Set.of("ROLE_USER"));

        when(authService.register(request)).thenReturn(userDTO);

        // => Act
        ResponseEntity<?> response = authController.register(request);

        // => Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(authService, times(1)).register(request);
    }

    @Test
    @DisplayName("Deve retornar BAD_REQUEST ao tentar registrar um usuário já existente")
    void deveRetornarBadRequestQuandoUsuarioJaExiste() {
        // => Arrange
        when(webRequest.getDescription(false)).thenReturn("uri=/auth/register");

        // => Act
        ResponseEntity<ApiError> response =
                globalExceptionHandler.handleUsernameExists(
                        new UsernameAlreadyExistsException("Usuário já existe"),
                        webRequest
                );

        // => Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Usuário já existe", response.getBody().getMessage());
        assertEquals("Username já existe", response.getBody().getError());
        assertEquals("/auth/register", response.getBody().getPath());
    }

    // -------------------------------
    // TESTES DE LOGIN
    // -------------------------------

    @Test
    @DisplayName("Deve realizar login com sucesso e retornar token JWT")
    void deveFazerLoginComSucesso() {
        // => Arrange
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("lucas@email.com");
        loginRequest.setPassword("123456");

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken("token123");
        loginResponse.setRoles(Set.of("ROLE_USER"));

        when(authService.login(loginRequest)).thenReturn(loginResponse);

        // => Act
        ResponseEntity<?> response = authController.login(loginRequest);

        // => Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(loginResponse, response.getBody());
        verify(authService, times(1)).login(loginRequest);
    }

    @Test
    @DisplayName("Deve retornar UNAUTHORIZED quando as credenciais forem inválidas")
    void deveRetornarUnauthorizedQuandoCredenciaisInvalidas() {
        // => Arrange
        when(webRequest.getDescription(false)).thenReturn("uri=/auth/login");

        // => Act
        ResponseEntity<ApiError> response = globalExceptionHandler
                .handleInvalidCredentials(
                        new InvalidCredentialException("Credenciais inválidas"),
                        webRequest
                );

        // => Assert
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Credenciais inválidas", response.getBody().getMessage());
        assertEquals("Credenciais inválidas", response.getBody().getError());
        assertEquals("/auth/login", response.getBody().getPath());
    }
}
