package med3ir0s.userService.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Set;

// => DTO usado para retornar informações do usuário sem expor senha
public class UserDTO {
    private Long id;
    @NotBlank(message = "O campo 'username' é obrigatório.")
    @Size(min = 3, max = 30, message = "O username deve ter entre 3 e 30 caracteres.")
    private String username;
    @NotBlank(message = "O campo 'email' é obrigatório.")
    @Email(message = "O email informado é inválido.")
    private String email;
    @Size(min = 6, max = 60, message = "A senha deve ter pelo menos 6 caracteres.")
    private String password;
    private Set<String> roles;

    // => Construtor padrão necessário para o Jackson
    public UserDTO(){}

    // => Getters
    public Long getId(){ return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public Set<String> getRoles() { return roles; }
    public String getPassword() { return password; }

    // => Setters
    public void setId(Long id){ this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setEmail(String email){ this.email = email; }
    public void setRoles(Set<String> roles){ this.roles = roles; }
    public void setPassword(String password) { this.password = password; }
}
