package com.example.productApp.loginAndSignup.controller;

import com.example.productApp.loginAndSignup.model.Users;
import com.example.productApp.loginAndSignup.service.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {
    @Autowired
    private UsersServices usersServices;
    @PostMapping("/register")
    public Users usersRegisteration(@RequestBody Users user){
        return usersServices.usersRegistration(user);
    }
    @PostMapping("/login")
    public String userLogin(@RequestBody Users user){
        return usersServices.userLoginVerify(user);
    }
}
