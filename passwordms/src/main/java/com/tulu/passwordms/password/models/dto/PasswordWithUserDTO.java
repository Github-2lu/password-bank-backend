package com.tulu.passwordms.password.models.dto;

import com.tulu.passwordms.password.models.Password;
import com.tulu.passwordms.password.models.externals.User;

import java.util.ArrayList;
import java.util.List;

public class PasswordWithUserDTO {
    private Long id;
    private String name;
    private String description;
    private String code;
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
