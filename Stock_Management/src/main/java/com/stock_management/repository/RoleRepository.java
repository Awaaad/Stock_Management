package com.stock_management.repository;

import com.stock_management.entity.Role;
import com.stock_management.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByRole(String role);
}
