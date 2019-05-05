package com.example.android.project.entity.request;

import java.io.Serializable;

/**
 * date: 2019/2/12
 * desc: 登录body
 */
public class LoginRequest implements Serializable{

    private String username;
    private String password;

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
