package com.example.demo;

import com.example.demo.document.LoginInfo;
import com.example.demo.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class ApiPracApplication {
    @Autowired
    LoginRepository loginRepository;

    public static void main(String[] args) {
        SpringApplication.run(ApiPracApplication.class, args);
    }

    @Component
    class CustomCommandLineRunner implements CommandLineRunner {
        @Override
        public void run(String...args) throws Exception {
            loginRepository.save(new LoginInfo("user", "pass", 0));
            loginRepository.save(new LoginInfo("admin", "pass", 1));
        }
    }
}

