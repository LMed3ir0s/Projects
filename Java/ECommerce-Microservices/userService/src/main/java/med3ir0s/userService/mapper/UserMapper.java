package med3ir0s.userService.mapper;

import med3ir0s.userService.dto.UserDTO;
import med3ir0s.userService.dto.UserProfileDTO;
import med3ir0s.userService.model.Role;
import med3ir0s.userService.model.User;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    // => Converte User -> UserDTO
    public UserDTO toDTO(User user) {
        if (user == null) return null;

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toSet())
        );
        return dto;
    }

    public UserProfileDTO toProfileDTO(User user) {
        if (user == null) return null;

        UserProfileDTO dto = new UserProfileDTO();
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
