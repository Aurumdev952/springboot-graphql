package com.job.post.jobpost.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RegisterRequest(
        @NotNull
        @NotEmpty
        String username,
        @Email
        String email,
        @NotEmpty
        @NotNull
        String password,
        String firstName,
        String lastName
) {
}
