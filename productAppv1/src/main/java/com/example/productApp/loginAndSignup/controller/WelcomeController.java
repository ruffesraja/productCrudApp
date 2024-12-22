package com.example.productApp.loginAndSignup.controller;



import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @GetMapping("/")
    public String welcome(HttpServletRequest req){
        return "welcome "+req.getSession().getId();
    }
}
