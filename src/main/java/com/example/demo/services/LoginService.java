package com.example.demo.services;

import com.example.demo.document.LoginInfo;
import com.example.demo.repository.LoginRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service("customerService")
public class LoginService {
    @Autowired
    LoginRepository loginRepository;

    public String login(String username, String password) {

        Optional<LoginInfo> optionalLoginInfo = Optional.ofNullable(loginRepository.findByUsernameAndPassword(username, password));
        if (optionalLoginInfo.isPresent()) {
            String token = UUID.randomUUID().toString();
            LoginInfo loginInfo1 = optionalLoginInfo.get();
            loginInfo1.setToken(token);
            loginRepository.save(loginInfo1);
            return token;
        }
        return StringUtils.EMPTY;
    }

    public Optional findByToken(String token) {

        Optional<LoginInfo> optionalLoginInfo = Optional.ofNullable(loginRepository.findByToken(token));
        if (optionalLoginInfo.isPresent()) {
            LoginInfo loginInfo = optionalLoginInfo.get();

            if (loginInfo.getRole() == LoginInfo.ADMIN) {
                User user = new User(loginInfo.getUsername(), loginInfo.getPassword(), true, true,
                        true, true,
                        AuthorityUtils.commaSeparatedStringToAuthorityList("USER, ADMIN"));
                return Optional.of(user);
            }

            User user = new User(loginInfo.getUsername(), loginInfo.getPassword(), true,
                    true, true, true,
                    AuthorityUtils.createAuthorityList("USER"));
            return Optional.of(user);
        }
        return Optional.empty();
    }
}

