package com.whut.smart.support.shiro.oAuth2.dto;

import java.io.Serializable;

/**
 * 对应oauth2_user表
 *
 * Created by null on 2017/1/1.
 */
public class OAuthUserDto implements Serializable {

    private Long id; //编号
    private String username; //用户名
    private String password; //密码
    private String salt; //加密密码的盐

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "OAuthUserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
