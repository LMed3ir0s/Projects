package med3ir0s.userService.controller;

import jakarta.validation.Valid;
import med3ir0s.userService.dto.LoginRequest;
import med3ir0s.userService.dto.LoginResponse;
import med3ir0s.userService.dto.RegisterRequest;
import med3ir0s.userService.dto.UserDTO;
import med3ir0s.userService.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authTests")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    // => Endpoint para regirar novo usuario
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody RegisterRequest request){
        UserDTO newUser = authService.register(request);
        return ResponseEntity.ok(newUser);
    }

    // => Endpoint para login
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
