package com.example.demo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customer")
public class LoginInfo{
    static int ADMIN = 1;
    static int USER = 0;
    @Id
    String username;
    String password;
    int role;
    String token;

    public LoginInfo(String username, String password, int role){
        this.password = password;
        this.role = role;
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static int getADMIN() {
        return ADMIN;
    }

    public static void setADMIN(int ADMIN) {
        LoginInfo.ADMIN = ADMIN;
    }

    public static int getUSER() {
        return USER;
    }

    public static void setUSER(int USER) {
        LoginInfo.USER = USER;
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
