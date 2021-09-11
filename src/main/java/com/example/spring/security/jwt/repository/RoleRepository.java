package com.example.spring.security.jwt.repository;

import java.util.Optional;

import com.example.spring.security.jwt.models.ERole;
import com.example.spring.security.jwt.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
