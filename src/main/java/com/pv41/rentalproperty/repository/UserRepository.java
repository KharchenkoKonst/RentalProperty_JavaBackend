package com.pv41.rentalproperty.repository;

import com.pv41.rentalproperty.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
