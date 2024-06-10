package com.job.post.jobpost.controllers;

import com.job.post.jobpost.dtos.CreateReviewInput;
import com.job.post.jobpost.dtos.UpdateReviewInput;
import com.job.post.jobpost.models.Job;
import com.job.post.jobpost.models.Review;
import com.job.post.jobpost.models.User;
import com.job.post.jobpost.serviceImpl.JobService;
import com.job.post.jobpost.serviceImpl.ReviewService;
import com.job.post.jobpost.services.UserService;
import com.job.post.jobpost.utils.AuthUtil;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;


@AllArgsConstructor
@Controller
public class ReviewController {
    private final JobService jobService;
    private final AuthUtil authUtil;
    private final ReviewService reviewService;
    private final UserService userService;

    @QueryMapping
    public Review review(@Argument long id) {
        try {
            return reviewService.findById(id).orElseThrow(() -> new Exception("Not found"));
        } catch (Exception e) {
            return null;
        }
    }

    @QueryMapping
    public List<Review> reviews() {
        return reviewService.findAll();
    }

    @SchemaMapping
    public Job job(Review review) {
        return review.getJob();
    }
    @SchemaMapping
    public User reviewee(Review review) {
        return review.getReviewee();
    }
    @SchemaMapping
    public User reviewer(Review review) {
        return review.getReviewer();
    }

    @MutationMapping
    public Review createReview(@Argument CreateReviewInput input) throws Exception {
        User reviewee = authUtil.getCurrentUser();
        User reviewer = userService.getUser((int) input.reviewerId());
        if (reviewee == null || reviewer == null) {
            throw new Exception("User not found");
        }
        Job job = jobService.findById(input.jobId());
        if (job == null) {
            throw new Exception("Job not found");
        }
        Review review = new Review();
        review.setJob(job);
        review.setReviewer(reviewer);
        review.setReviewee(reviewee);
        review.setComment(input.comment());
        review.setRating(input.rating());
        reviewService.save(review);
        return review;
    }

    @MutationMapping
    public Review updateReview(@Argument long id, @Argument UpdateReviewInput input) throws Exception {
        Review review = reviewService.findById(id).orElseThrow(() -> new Exception("Not found"));
        User reviewee = authUtil.getCurrentUser();
        User reviewer = userService.getUser((int) input.reviewerId());
        if (reviewee == null || reviewer == null) {
            throw new Exception("User not found");
        }
        Job job = jobService.findById(input.jobId());
        if (job == null) {
            throw new Exception("Job not found");
        }
        review.setJob(job);
        review.setReviewer(reviewer);
        review.setReviewee(reviewee);
        review.setComment(input.comment());
        review.setRating(input.rating());
        reviewService.save(review);
        return review;
    }

    public boolean deleteReview(@Argument int id) {
        reviewService.deleteById((long) id);
        return true;
    }

}
