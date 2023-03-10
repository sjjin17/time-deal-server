package com.timedeal_server.timedeal.domain.user.repository;

import com.timedeal_server.timedeal.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    boolean existsByUsername(String username);
}
