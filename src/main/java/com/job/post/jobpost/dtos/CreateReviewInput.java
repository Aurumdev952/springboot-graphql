package com.job.post.jobpost.dtos;

import jakarta.validation.constraints.NotNull;

public record CreateReviewInput(
        @NotNull
        long jobId,
        @NotNull
        long reviewerId,
        @NotNull
        int rating,
        @NotNull
        String comment
) {
}
