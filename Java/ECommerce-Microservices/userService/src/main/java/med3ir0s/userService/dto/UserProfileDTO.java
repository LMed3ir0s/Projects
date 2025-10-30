package med3ir0s.userService.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// => DTO usado para retornar informações do usuário (username e email)
public class UserProfileDTO {
    @NotBlank(message = "O campo 'username' é obrigatório.")
    @Size(min = 3, max = 30, message = "O username deve ter entre 3 e 30 caracteres.")
    private String username;
    @NotBlank(message = "O campo 'email' é obrigatório.")
    @Email(message = "O email informado é inválido.")
    private String email;

    // => Construtor padrão necessário para o Jackson
    public UserProfileDTO(){}

    // => Getters
    public String getUsername() {return username; }
    public String getEmail() { return email; }

    // => Setters
    public void setUsername(String username) { this.username = username; }
    public void setEmail(String email) { this.email = email; }
}
