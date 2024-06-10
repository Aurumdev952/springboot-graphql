package com.job.post.jobpost.repositories;


import com.job.post.jobpost.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    boolean existsByEmail(String email);
}
