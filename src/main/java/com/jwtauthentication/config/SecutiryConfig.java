package com.jwtauthentication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jwtauthentication.security.JwtAuthenticationEntryPoint;
import com.jwtauthentication.security.JwtAuthenticationFilter;

@Configuration
public class SecutiryConfig {
    @Autowired
    private JwtAuthenticationEntryPoint point;

    @Autowired 
    private JwtAuthenticationFilter filter;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("hello");
        http.csrf(csrf -> csrf.disable())
            .cors(cors -> cors.disable())
            .authorizeHttpRequests(auth->auth.requestMatchers("/home/**").authenticated().requestMatchers("/auth/login").permitAll().requestMatchers("/auth/create").permitAll().anyRequest().authenticated())
            .exceptionHandling(e->e.authenticationEntryPoint(point))
            .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(this.userDetailsService); 
        return daoAuthenticationProvider;
    }
}
