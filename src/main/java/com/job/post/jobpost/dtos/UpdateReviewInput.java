package com.job.post.jobpost.dtos;

import jakarta.validation.constraints.NotNull;

public record UpdateReviewInput(
        long jobId,
        long reviewerId,
        int rating,
        String comment
) {
}
