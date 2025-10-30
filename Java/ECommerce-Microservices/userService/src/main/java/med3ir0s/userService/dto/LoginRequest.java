package med3ir0s.userService.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;

// => DTO usado para receber dados de login.
public class LoginRequest {
    @NotBlank(message = "O campo 'email' é obrigatório.")
    @Email(message = "O email informado é inválido.")
    private String email;
    @NotBlank(message = "O campo 'password' é obrigatório.")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres.")
    private String password;

    // Construtor padrão necessário para o Jackson
    public LoginRequest(){}

    // => Getters
    public String getEmail(){ return email; }
    public String getPassword(){ return password; }

    // => Setters
    public void setEmail(String email){ this.email = email; }
    public void setPassword(String password){ this.password = password; }


}
