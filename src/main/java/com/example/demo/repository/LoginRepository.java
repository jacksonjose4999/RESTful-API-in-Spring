package com.example.demo.repository;

import com.example.demo.document.LoginInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoginRepository extends MongoRepository<LoginInfo, String> {
    LoginInfo findByUsernameAndPassword(String username, String password);
    LoginInfo findByToken(String token);
}
