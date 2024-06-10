package com.job.post.jobpost.dtos;

import com.job.post.jobpost.models.JobStatus;
import jakarta.validation.constraints.NotNull;

public record CreateJobInput(
        @NotNull
        String title,
        @NotNull
        String      description,
        @NotNull
        String         category,
        @NotNull
        Float         price,
        @NotNull
        JobStatus status
        ) {
}
