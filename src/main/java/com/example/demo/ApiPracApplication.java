package com.example.demo;

import com.example.demo.document.User;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
public class ApiPracApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiPracApplication.class, args);
    }

    @Configuration
    @EnableWebSecurity
    public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
        }
    }
    @Bean
    CommandLineRunner runner(UserRepository userRepository){
        return args -> {
            System.out.println(userRepository.findByNameAndAddress("Jackson", "Earth").getAge());
        };
    }

}
