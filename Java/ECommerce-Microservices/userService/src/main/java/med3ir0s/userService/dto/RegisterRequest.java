package med3ir0s.userService.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Set;

// => DTO usado para receber os dados do usuário no cadastro
public class RegisterRequest {
        @NotBlank(message = "O campo 'username' é obrigatório.")
        @Size(min = 3, max = 30, message = "O username deve ter entre 3 e 30 caracteres.")
        private String username;
        @NotBlank(message = "O campo 'email' é obrigatório.")
        @Email(message = "O email informado é inválido.")
        private String email;
        @NotBlank(message = "O campo 'password' é obrigatório.")
        @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres.")
        private String password;
        private Set<String> roles;  // Roles desejadas para o usuário

        // => Construtor padrão necessário para JPA/Jackson
        public RegisterRequest(){}

        // => Getters
        public String getUsername(){ return username; }
        public String getEmail(){ return email; }
        public String getPassword(){ return password; }
        public Set<String> getRoles(){ return roles; }

        // => Setters
        public void setUsername(String username) { this.username = username; }
        public void setEmail(String email){ this.email = email; }
        public void setPassword(String password){ this.password = password; }
        public void setRoles(Set<String> roles){ this.roles = roles; }
}
