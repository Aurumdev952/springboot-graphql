package com.job.post.jobpost.services;


import com.job.post.jobpost.dtos.RegisterRequest;
import com.job.post.jobpost.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);
    void saveUser(RegisterRequest data);
    User getUser(int id);
    List<User> getAllUsers();
}