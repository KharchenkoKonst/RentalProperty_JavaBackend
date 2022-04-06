package com.pv41.rentalproperty.repository;

import com.pv41.rentalproperty.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
