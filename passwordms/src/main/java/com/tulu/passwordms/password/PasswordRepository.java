package com.tulu.passwordms.password;

import com.tulu.passwordms.password.models.Password;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PasswordRepository extends JpaRepository<Password, Long> {
    List<Password> findByUserId(Long userId);
}
