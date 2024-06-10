package com.job.post.jobpost.dtos;

import com.job.post.jobpost.models.JobStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateJobInput(
        String title,
        String      description,
        String         category,
        Float         price,
        JobStatus status
) {
}
