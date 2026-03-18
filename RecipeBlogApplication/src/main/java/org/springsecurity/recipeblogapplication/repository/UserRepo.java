package org.springsecurity.recipeblogapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springsecurity.recipeblogapplication.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
