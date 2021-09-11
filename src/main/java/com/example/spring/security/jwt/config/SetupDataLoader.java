package com.example.spring.security.jwt.config;

import com.example.spring.security.jwt.models.ERole;
import com.example.spring.security.jwt.models.Role;
import com.example.spring.security.jwt.models.User;
import com.example.spring.security.jwt.repository.RoleRepository;
import com.example.spring.security.jwt.repository.UserRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        // Create user roles
        var userRole = createRoleIfNotFound(ERole.ROLE_USER);
        var moderatorRole = createRoleIfNotFound(ERole.ROLE_MODERATOR);
        var adminRole = createRoleIfNotFound(ERole.ROLE_ADMIN);

        //Create users
        createUserIfNotFound("user", userRole, "user@user.com");
        createUserIfNotFound("moderator", moderatorRole, "mod@moderator.com");
        createUserIfNotFound("admin", adminRole, "admin@admin.com");

        alreadySetup = true;
    }

    @Transactional
    private final Role createRoleIfNotFound(final ERole name) {
        Role role = roleRepository.findByName(name).orElse(null);
        if (role == null) {
            role = new Role(name);
            role = roleRepository.save(role);
        }
        return role;
    }

    @Transactional
    private final User createUserIfNotFound(final String name, final Role role, final String email) {
        User user = userRepository.findByUsername(name).orElse(null);
        if (user == null) {
            user = new User(name,email,"$2a$10$MffP7TPRDr1kLqeHHEpmDuHYRmaZmzaa5xJYgcQkuyEnR3n5c.1wy");//123456
            user.setRoles(Set.of(role));
            user = userRepository.save(user);
        }
        return user;
    }
}