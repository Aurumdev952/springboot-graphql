package com.job.post.jobpost.dtos;

import com.job.post.jobpost.models.BidStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateBidInput(
        int jobId,
        float offerPrice,
        String message,
        BidStatus status
) {
}
