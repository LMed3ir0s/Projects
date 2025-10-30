package med3ir0s.userService.controller;

import jakarta.validation.Valid;
import med3ir0s.userService.dto.UserDTO;
import med3ir0s.userService.dto.UserProfileDTO;
import med3ir0s.userService.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    // => Helper: pega o username do token (SecurityContext)
    private String getAuthenticateUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null ? auth.getName() : null;
    }

    // => [USER] Profile do user
    @GetMapping("profile/me")
    public ResponseEntity<UserProfileDTO> getMyProfile(){
        String username = getAuthenticateUsername();
        UserProfileDTO dto = userService.getUserInfo(username);
        return ResponseEntity.ok(dto);
    }

    // => [USER] Atualiza username/email/senha
    @PutMapping("profile/me")
    public ResponseEntity<UserProfileDTO> updateMyProfile(@Valid @RequestBody UserDTO request){
        String username = getAuthenticateUsername();
        UserProfileDTO updated = userService.updateUserInfo(username, request);
        UserProfileDTO profile = new UserProfileDTO();
        profile.setUsername(updated.getUsername());
        profile.setEmail(updated.getEmail());
        return ResponseEntity.ok(profile);
    }

    // => [USER] Deleta profile
    @DeleteMapping("/profile/me")
    public ResponseEntity<Void> deleteMyAccount(){
        String username = getAuthenticateUsername();
        userService.deleteAccount(username);
        return ResponseEntity.noContent().build();
    }

    // => [ADMIN] Lista todos usuarios
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<Set<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.findAll());
    }

    // => [ADMIN] Busca por id
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    // => [ADMIN] Busca por username
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/username/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok((userService.findByUsername(username)));
    }

    // => [ADMIN] Busca por email
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.findByEmail(email));
    }

// => [ADMIN] Atualiza user por id
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/{id}")
    public ResponseEntity<UserDTO> updateUserByAdmin(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        UserDTO updated = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updated);
    }

// => [ADMIN] Deleta user por id
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> deleteUserByAdmin(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
