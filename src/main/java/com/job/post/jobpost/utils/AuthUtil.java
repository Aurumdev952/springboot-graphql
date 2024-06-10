package com.job.post.jobpost.utils;

import com.job.post.jobpost.models.User;
import com.job.post.jobpost.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Component
public class AuthUtil {
    private final UserRepo userRepo;

    public User getCurrentUser() throws Exception {

            SecurityContext securityContext = SecurityContextHolder.getContext();
            Authentication authentication = securityContext.getAuthentication();

            if (authentication != null && authentication.isAuthenticated()) {
                Object principal = authentication.getPrincipal();
                if (principal instanceof UserDetails userDetails) {

                    return userRepo.findByUsername(userDetails.getUsername());
                } else {
                    throw new Exception("something went wrong");
                }
            } else {
                throw new Exception("User not authenticated");
            }
    }
}
