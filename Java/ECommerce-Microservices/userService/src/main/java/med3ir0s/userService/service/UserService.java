package med3ir0s.userService.service;

import med3ir0s.userService.dto.UserDTO;
import med3ir0s.userService.dto.UserProfileDTO;
import med3ir0s.userService.exception.UserNotFoundException;
import med3ir0s.userService.exception.UsernameAlreadyExistsException;
import med3ir0s.userService.mapper.UserMapper;
import med3ir0s.userService.model.User;
import med3ir0s.userService.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    // => [ADMIN] Consultas
    public UserDTO findById (Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário com ID " + id + " não encontrado."));
        return userMapper.toDTO(user);
    }

    public UserDTO findByUsername(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Usuário com username '" + username + "' não encontrado."));
        return userMapper.toDTO(user);
    }

    public UserDTO findByEmail(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Usuário com e-mail '" + email + "' não encontrado."));
        return userMapper.toDTO(user);
    }

    public Set<UserDTO> findAll(){
        return userRepository.findAll().stream().map(userMapper::toDTO).collect(Collectors.toSet());
    }

    // => [ADMIN] Atualizações e remoções
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuário com ID " + id + " não encontrado."));
        if (userDTO.getUsername() != null && userRepository.existsByUsername(userDTO.getUsername())) {
            throw new UsernameAlreadyExistsException("O username '" + userDTO.getUsername() + "' já está em uso.");
        }

        if (userDTO.getEmail() != null && userRepository.existsByEmail(userDTO.getEmail())) {
            throw new UsernameAlreadyExistsException("O e-mail '" + userDTO.getEmail() + "' já está em uso.");
        }
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        userRepository.save(user);
        return userMapper.toDTO(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new  UserNotFoundException("Não foi possível deletar: usuário com ID " + id + " não existe.");
        }
        userRepository.deleteById(id);
    }

    // => [USER] Informações propio perfil sem ID
    public UserProfileDTO getUserInfo(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("Usuário '" + username + "' não encontrado."));
        return userMapper.toProfileDTO(user);
    }

    // => [USER] Atualização (e-mail, senha, username)
    public UserProfileDTO updateUserInfo(String username, UserDTO request) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("Usuário '" + username + "' não encontrado."));

        if (request.getUsername() != null && userRepository.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyExistsException("O username '" + request.getUsername() + "' já está em uso.");
        }
        if (request.getEmail() != null && userRepository.existsByEmail(request.getEmail())) {
            throw new UsernameAlreadyExistsException("O e-mail '" + request.getEmail() + "' já está em uso.");
        }

        if (request.getUsername() != null) user.setUsername(request.getUsername());
        if (request.getEmail() != null) user.setEmail(request.getEmail());
        if (request.getPassword() != null) user.setPassword(passwordEncoder.encode(request.getPassword()));

        User updated = userRepository.save(user);
        return userMapper.toProfileDTO(updated);
    }

    // => [USER] Deletar propia conta
    public void deleteAccount(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("Usuário '" + username + "' não encontrado."));
        userRepository.delete(user);
    }
}
