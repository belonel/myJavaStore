package com.lider.BlockNoteWebApp.repos;

import com.lider.BlockNoteWebApp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    Optional<User> findById(Long id);

    User findByActivationCode(String code);
}
