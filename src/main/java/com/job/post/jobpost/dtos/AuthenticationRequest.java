package com.job.post.jobpost.dtos;

public record AuthenticationRequest(
        String username,
        String password
) {
}
