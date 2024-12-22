package com.example.productApp.loginAndSignup.service;

import com.example.productApp.loginAndSignup.model.UserPrincipal;
import com.example.productApp.loginAndSignup.model.Users;
import com.example.productApp.loginAndSignup.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepo repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repo.findByUsername(username);
        if(user==null){
            System.out.println("user not found");
            throw new UsernameNotFoundException("user not fount");
        }
        return new UserPrincipal(user);
    }
}
