package com.example.twb.controller;

import com.example.twb.entity.Response;
import com.example.twb.entity.User;
import com.example.twb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Response registerUser(@RequestBody User user) {
        if(this.userService.registerUser(user)) {
            return new Response("Ok");
        }

        return new Response("Conflict");
    }
}
