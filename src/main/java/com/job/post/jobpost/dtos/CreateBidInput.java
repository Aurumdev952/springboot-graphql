package com.job.post.jobpost.dtos;

import com.job.post.jobpost.models.BidStatus;
import jakarta.validation.constraints.NotNull;

public record CreateBidInput(
        @NotNull
        int jobId,
        @NotNull
        float offerPrice,
        @NotNull
        String message,
        @NotNull
        BidStatus status
) {
}
