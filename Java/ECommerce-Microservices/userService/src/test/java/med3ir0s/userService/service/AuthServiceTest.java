package med3ir0s.userService.service;

import med3ir0s.userService.dto.LoginRequest;
import med3ir0s.userService.dto.LoginResponse;
import med3ir0s.userService.dto.RegisterRequest;
import med3ir0s.userService.dto.UserDTO;
import med3ir0s.userService.exception.InvalidCredentialException;
import med3ir0s.userService.exception.RoleNotFoundException;
import med3ir0s.userService.exception.UserNotFoundException;
import med3ir0s.userService.exception.UsernameAlreadyExistsException;
import med3ir0s.userService.model.Role;
import med3ir0s.userService.model.User;
import med3ir0s.userService.repository.RoleRepository;
import med3ir0s.userService.repository.UserRepository;
import med3ir0s.userService.security.JWTUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes do AuthService")
public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JWTUtils jwtUtils;

    @InjectMocks
    private AuthService authService;

    // -------------------------------
    // TESTES DE REGISTRO
    // -------------------------------
    @Test
    @DisplayName("Deve Registrar novo usuário com sucesso")
    void deveRegistrarNovoUsuarioComSucesso() {
        // => Arrange
        RegisterRequest request = new RegisterRequest();
        request.setUsername("Lucas");
        request.setEmail("lucas@email.com");
        request.setPassword("1234567890");

        Role userRole = new Role();
        userRole.setName("ROLE_USER");

        // => Act
        when(userRepository.existsByUsername("Lucas")).thenReturn(false);
        when(userRepository.existsByEmail("lucas@email.com")).thenReturn(false);
        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.of(userRole));
        when(passwordEncoder.encode("1234567890")).thenReturn("encoded");
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        UserDTO result = authService.register(request);

        // => Assert
        assertEquals("Lucas", result.getUsername());
        assertEquals("lucas@email.com", result.getEmail());
        assertTrue(result.getRoles().contains("ROLE_USER"));
        verify(userRepository).save(any(User.class));
    }

    @Test
    @DisplayName("Deve lançar excecção Username já em uso")
    void deveLancarExcecaoSeUsernameJaExistir() {
        // => Arrange
        RegisterRequest request = new RegisterRequest();
        request.setUsername("Lucas");
        request.setEmail("lucas@email.com");
        request.setPassword("123");

        // => Act
        when(userRepository.existsByUsername("Lucas")).thenReturn(true);

        // => Assert
        assertThrows(UsernameAlreadyExistsException.class, () -> authService.register(request));
        verify(userRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar Excecção E-mail já em uso")
    void deveLancarExcecaoSeEmailJaExistir() {
        // => Arrange
        RegisterRequest request = new RegisterRequest();
        request.setUsername("Lucas");
        request.setEmail("lucas@email.com");
        request.setPassword("123");

        // => Act
        when(userRepository.existsByUsername("Lucas")).thenReturn(false);
        when(userRepository.existsByEmail("lucas@email.com")).thenReturn(true);

        // => Assert
        assertThrows(UsernameAlreadyExistsException.class, () -> authService.register(request));
        verify(userRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar Excecção Role não encontrada")
    void deveLancarExcecaoSeRoleUserNaoEncontrada() {
        // => Arrange
        RegisterRequest request = new RegisterRequest();
        request.setUsername("Lucas");
        request.setEmail("lucas@email.com");
        request.setPassword("123456");

        // => Act
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.empty());

        // => Assert
        assertThrows(RoleNotFoundException.class, () -> authService.register(request));
    }

    // -------------------------------
    // TESTES DE LOGIN
    // -------------------------------
    @Test
    @DisplayName("Deve realizar login e retornar token JWT")
    void deveLogarComSucessoERetornarToken() {
        // => Arrange
        LoginRequest request = new LoginRequest();
        request.setEmail("lucas@email.com");
        request.setPassword("123456");

        Role role = new Role();
        role.setName("ROLE_USER");

        User user = new User();
        user.setUsername("Lucas");
        user.setEmail("lucas@email.com");
        user.setPassword("encoded");
        user.setRoles(Set.of(role));

        // => Act
        when(userRepository.findByEmail("lucas@email.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("123456", "encoded")).thenReturn(true);
        when(jwtUtils.generateToken("Lucas", Set.of("ROLE_USER"))).thenReturn("fakeToken123");

        LoginResponse response = authService.login(request);

        // => Assert
        assertEquals("fakeToken123", response.getToken());
        assertTrue(response.getRoles().contains("ROLE_USER"));
        verify(jwtUtils).generateToken("Lucas", Set.of("ROLE_USER"));
    }

    @Test
    @DisplayName("Deve realizar login e lançar Excecção E-mail não encontrado")
    void deveLancarExcecaoSeEmailNaoForEncontrado() {
        // => Arrange
        LoginRequest request = new LoginRequest();
        request.setEmail("naoexiste@email.com");
        request.setPassword("123");

        // => Act
        when(userRepository.findByEmail("naoexiste@email.com")).thenReturn(Optional.empty());

        // => Assert
        assertThrows(UserNotFoundException.class, () -> authService.login(request));
    }

    @Test
    @DisplayName("Deve realizar login e lançar Excecção Senha inválida")
    void deveLancarExcecaoSeSenhaForInvalida() {
        // => Arrange
        LoginRequest request = new LoginRequest();
        request.setEmail("lucas@email.com");
        request.setPassword("errada");

        User user = new User();
        user.setUsername("Lucas");
        user.setEmail("lucas@email.com");
        user.setPassword("encoded");

        // => Act
        when(userRepository.findByEmail("lucas@email.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("errada", "encoded")).thenReturn(false);

        // => Assert
        assertThrows(InvalidCredentialException.class, () -> authService.login(request));
    }
}

