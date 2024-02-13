package com.jwtauthentication.controller;

import org.springframework.web.bind.annotation.RestController;

import com.jwtauthentication.models.User;
import com.jwtauthentication.services.UserService;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/home")
public class Home {
    @Autowired
    private UserService userService;

    @GetMapping("/welcome")
    public List<User> Welcome(){
        System.out.println("Punit Tewani");
        return this.userService.getAllUsers();
    }

    @GetMapping("/current-user")
    public String getLoggedInUser(Principal principal){
        return principal.getName();
    }
}
