package com.whut.smart.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by null on 2016/12/30.
 */
public class UserDto implements Serializable {

    private String id;

    @NotEmpty
    @Pattern(regexp = "[a-zA-Z_ 0-9]{6,18}")
    private String username;

    @NotEmpty
    @Length(min = 8, max = 18)
    private String password;

    @Transient
    private Boolean rememberMe = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
