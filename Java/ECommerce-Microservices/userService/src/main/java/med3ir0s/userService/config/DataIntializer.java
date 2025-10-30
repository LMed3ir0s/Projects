package med3ir0s.userService.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import med3ir0s.userService.model.Role;
import med3ir0s.userService.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataIntializer {

    private static final Logger log = LoggerFactory.getLogger(DataIntializer.class);
    private final RoleRepository roleRepository;

    public DataIntializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        log.info("Inicializando roles padrão...");
        createRoleIfNotExists("ROLE_USER");
        createRoleIfNotExists("ROLE_ADMIN");
        log.info("Roles padrão garantidas no banco de dados.");
    }

    private void createRoleIfNotExists(String rolename) {
        roleRepository.findByName(rolename)
                .orElseGet(() -> {
                    Role role = new Role();
                    roleRepository.save(role);
                    log.info("Role criada: {}", rolename);
                    return role;
                });
    }
}
