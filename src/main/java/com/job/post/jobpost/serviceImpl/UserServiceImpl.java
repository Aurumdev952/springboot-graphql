package com.job.post.jobpost.serviceImpl;


import com.job.post.jobpost.dtos.RegisterRequest;
import com.job.post.jobpost.models.User;
import com.job.post.jobpost.repositories.UserRepo;
import com.job.post.jobpost.services.UserService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepo userRepository;

    public UserServiceImpl(UserRepo userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>()
        );
    }


    @Override
    public void saveUser(RegisterRequest data) {
        boolean checkUserExists = userRepository.existsByEmail(data.email());
        if (checkUserExists) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
        }
        User user = new User();
        user.setUsername(data.username());
        user.setEmail(data.email());
        user.setFirstName(data.firstName());
        user.setLastName(data.lastName());
        user.setPassword(passwordEncoder.encode(data.password()));
        userRepository.save(user);
    }

    @Override
    public User getUser(int id) {
        try {
            return userRepository.findById(id).orElseThrow(() -> new Exception("User not found: " + id));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}