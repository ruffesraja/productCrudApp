package com.example.productApp.loginAndSignup.repo;

import com.example.productApp.loginAndSignup.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepo extends JpaRepository<Users,Integer> {
    Users findByUsername(String username);
}
