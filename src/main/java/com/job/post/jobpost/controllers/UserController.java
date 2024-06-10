package com.job.post.jobpost.controllers;

import com.job.post.jobpost.models.User;
import com.job.post.jobpost.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@AllArgsConstructor
@Controller
public class UserController {

    private final UserService userService;


    @QueryMapping
    public User user(@Argument int id) {
        return userService.getUser(id);
    }

    @QueryMapping
    public List<User> users() {
        return userService.getAllUsers();
    }

}
