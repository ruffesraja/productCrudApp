package com.example.productApp.loginAndSignup.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity  // this will disable the default spring security
public class SecurityConfig {
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    private JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(customizer->customizer.disable());
        httpSecurity.authorizeHttpRequests(request->request
                .requestMatchers("/register","/login").permitAll().anyRequest().authenticated());
        //httpSecurity.formLogin(Customizer.withDefaults()); // for browser
        httpSecurity.httpBasic(Customizer.withDefaults()); // for API tools
        //below line to create new session id every request
        httpSecurity.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
//    @Bean     // for hard coded values of userDetailsService
//    public UserDetailsService userDetailsService(){
//        UserDetails user1 = User.withDefaultPasswordEncoder().username("yoki").password("12").roles("USER").build();
//        UserDetails user2 = User.withDefaultPasswordEncoder().username("raja").password("12").roles("ADMIN").build();
//
//        return new InMemoryUserDetailsManager(user1,user2);
//    }
    @Bean
    public AuthenticationProvider authendicationprovider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); // without encodeing the password

        provider.setPasswordEncoder(new BCryptPasswordEncoder(10));
        // with Brcrypt password encoder
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
    @Bean
    public AuthenticationManager authendicationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
} 
