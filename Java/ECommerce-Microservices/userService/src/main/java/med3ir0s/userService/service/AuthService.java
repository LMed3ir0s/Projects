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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtils;

    public AuthService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder,
                       JWTUtils jwtUtils) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    // => Registrar novo usuário (sempre ROLE_USER)
    public UserDTO register(RegisterRequest request) {
        // Verifica duplicidade
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyExistsException("O username '" + request.getUsername() + "' já está em uso.");
        }
        if (userRepository.existsByEmail(request.getEmail())){
            throw new UsernameAlreadyExistsException("O e-mail '" + request.getEmail() + "' já está em uso!");
        }

        // => Cria novo usuário
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // => Define role padrão (ROLE_USER)
        Role userRole = roleRepository.findByName("ROLE_USER").orElseThrow(
                () -> new RoleNotFoundException("Role padrão 'ROLE_USER' não encontrada!"));
        user.setRoles(Set.of(userRole));
        // => Salva usuário no banco
        User saveUser = userRepository.save(user);
        // => Retorna DTO sem expor senha
        return mapToUserDTO(saveUser);
    }

    // => Login
    public LoginResponse login(LoginRequest request) {
        // => Busca usuário por email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Usuário com e-mail '" + request.getEmail() +"' não encontrado!"));

        // => Valida senha
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialException("Senha inválida!");
        }

        // => Gera token JWT
        Set<String> roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
        String token = jwtUtils.generateToken(user.getUsername(), roles);

        // => Retorna token + roles
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setRoles(roles);
        return response;
    }

    // => Mapeia User -> UserDTO
    private UserDTO mapToUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRoles(user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet())
        );
        return dto;
    }
}
