package med3ir0s.userService.dto;

import java.util.Set;

// => DTO retornado após login bem-sucedido, contendo JWT e roles.
public class LoginResponse {
    private String token;
    private Set<String> roles;

    // => Construtor padrão necessário para JPA/Jackson
    public LoginResponse(){}

    // => Getters
    public String getToken(){ return token; }
    public Set<String> getRoles(){ return roles; }

    // => Setters
    public void setToken(String token){ this.token = token; }
    public void setRoles(Set<String> roles){ this.roles = roles; }
}
