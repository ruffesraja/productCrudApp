package com.example.productApp.loginAndSignup.service;

import com.example.productApp.loginAndSignup.model.Users;
import com.example.productApp.loginAndSignup.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersServices {
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;
    private BCryptPasswordEncoder encoder= new BCryptPasswordEncoder(10);
    public Users usersRegistration(Users user){
        String encryptedPassword = encoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        return usersRepo.save(user);
    }

    public String userLoginVerify(Users user) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(authentication.isAuthenticated()){
            //return "login success";
            return jwtService.generateToken(user.getUsername());
        }
        return "fail";
    }
}
