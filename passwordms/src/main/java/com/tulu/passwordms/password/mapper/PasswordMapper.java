package com.tulu.passwordms.password.mapper;

import com.tulu.passwordms.password.models.Password;
import com.tulu.passwordms.password.models.dto.PasswordWithUserDTO;
import com.tulu.passwordms.password.models.externals.User;

public class PasswordMapper {
    public static PasswordWithUserDTO mapToPasswordWithUserDTO(Password password, User user){
        PasswordWithUserDTO passwordWithUserDTO = new PasswordWithUserDTO();
        passwordWithUserDTO.setId(password.getId());
        passwordWithUserDTO.setName(password.getName());
        passwordWithUserDTO.setDescription(password.getDescription());
        passwordWithUserDTO.setCode(password.getCode());
        passwordWithUserDTO.setUser(user);

        return passwordWithUserDTO;
    }
}
