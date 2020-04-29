package com.example.demo.resource_controller;

import com.example.demo.document.LoginInfo;
import com.example.demo.services.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
public class TokenController {

    @Autowired
    private LoginService loginService;

    @GetMapping(value = "/token", params = {"username", "password"})
    public String getToken(@RequestParam("username") String username, @RequestParam("password") String password){
        String token= loginService.login(username,password);
        if(StringUtils.isEmpty(token)){
            throw new ResourceNotFoundException();
        }
        return token;
    }
}