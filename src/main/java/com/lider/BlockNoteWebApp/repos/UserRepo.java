package com.lider.BlockNoteWebApp.repos;

import com.lider.BlockNoteWebApp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
