package med3ir0s.userService.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import med3ir0s.userService.model.Role;
import med3ir0s.userService.model.User;
import med3ir0s.userService.repository.RoleRepository;
import med3ir0s.userService.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
public class AdminIntializer {
    private static final Logger log = LoggerFactory.getLogger(AdminIntializer.class);
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminIntializer (
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initAdmin() {
        userRepository.findByUsername("admin").ifPresentOrElse(
                u -> log.info("Usuário admin já existe."),
                () -> {
                    Role adminRole = roleRepository.findByName("ROLE_ADMIN").orElseThrow(() -> new RuntimeException("Role ADMIN não encontrada!"));
                    Role userRole = roleRepository.findByName("ROLE_USER").orElseThrow(() -> new RuntimeException("Role USER não encontrada!"));

                    User admin = new User();
                    admin.setUsername("admin");
                    admin.setEmail("admin@med3ir0s.dev");
                    admin.setPassword(passwordEncoder.encode("senhaadmin123456789"));
                    admin.setRoles(Set.of(adminRole, userRole));
                    userRepository.save(admin);
                    log.info("Usuário admin criado com sucesso: username='admin', senha='senhaadmin123456789`");
                }
        );
    }
}
