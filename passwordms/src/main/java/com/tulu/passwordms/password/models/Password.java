package com.tulu.passwordms.password.models;

import jakarta.persistence.*;

@Entity
@Table(name = "password_table")
public class Password {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String code;

//    @ManyToOne
    private Long userId;

    public Password(){}

    public Password(Long id, String name, String description, String code, Long userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.code = code;
        this.userId = userId;
    }

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
