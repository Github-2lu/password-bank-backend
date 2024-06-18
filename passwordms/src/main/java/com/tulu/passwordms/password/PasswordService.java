package com.tulu.passwordms.password;

import com.tulu.passwordms.password.models.Password;
import com.tulu.passwordms.password.models.dto.PasswordWithUserDTO;

import java.util.List;

public interface PasswordService {
    List<Password> findAll(Long userId);
    PasswordWithUserDTO findById(Long id);
    boolean addPassword(Long userId, Password password);
    boolean deletePasswordById(Long id);
    boolean updatePasswordById(Long id, Password newPassword);
}
