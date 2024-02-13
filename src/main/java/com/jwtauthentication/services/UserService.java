package com.jwtauthentication.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwtauthentication.models.User;
import com.jwtauthentication.repositories.UserRepository;

@Service
public class UserService {
    
    private List<User> store = new ArrayList<User>();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User createUser(User user){
        user.setUserid(UUID.randomUUID().toString());
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return  this.userRepository.save(user);
    }
}
