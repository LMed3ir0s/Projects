package med3ir0s.userService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users") // evita conflito com palavra reservada "user" no SQL
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles", // tabela intermediária
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    // => Construtor padrão necessário para JPA/Jackson
    public User(){}

    // => Getters
    public Long getId(){ return id; }
    public String getUsername(){ return username; }
    public String getEmail(){ return email; }
    public String getPassword(){ return password; }
    public Set<Role> getRoles() { return roles; }

    // => Setters
    public void setId(Long id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setEmail(String email){ this.email = email; }
    public void setPassword(String password){ this.password = password; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }
}
