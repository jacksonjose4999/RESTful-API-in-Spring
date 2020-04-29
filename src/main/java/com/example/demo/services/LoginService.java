package com.example.demo.services;

import com.example.demo.document.LoginInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service("customerService")
public class LoginService {

    ArrayList<LoginInfo> loginInfo;

    LoginService(){
        loginInfo = new ArrayList<>();
        loginInfo.add(new LoginInfo("user", "pass", 0));
        loginInfo.add(new LoginInfo("admin", "pass", 1));
    }

    public String login(String username, String password) {
        for (int i = 0; i < loginInfo.size(); i++){
            if (loginInfo.get(i).getUsername().compareTo(username)==0){
                if (loginInfo.get(i).getPassword().compareTo(password) == 0){
                    String token = UUID.randomUUID().toString();
                    loginInfo.get(i).setToken(token);
                    return token;
                }
            }
        }
        return StringUtils.EMPTY;
    }

    public Optional findByToken(String token) {
        for (int i = 0; i < loginInfo.size(); i++) {
            if (loginInfo.get(i).getToken().compareTo(token) == 0) {
                String auth = "ADMIN";
                if (loginInfo.get(i).getRole() == 0){
                    auth = "USER";
                }
                User user = new User(loginInfo.get(i).getUsername(), loginInfo.get(i).getPassword(), true, true, true, true,
                        AuthorityUtils.createAuthorityList(auth));
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}

