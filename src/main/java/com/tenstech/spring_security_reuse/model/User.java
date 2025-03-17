package com.tenstech.spring_security_reuse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "app_users")
@Getter
@Setter
public class User extends AuditedEntity{

    @Column(name = "user_name", unique = true)
    @NonNull
    private String username;

    @NonNull
    @Column(name = "email", unique = true)
    private String email;

    @NonNull
    @JsonIgnore
    private String password;


    @Override
    public String toString() {
        return "User{" +
                "userId='" + getId() + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
