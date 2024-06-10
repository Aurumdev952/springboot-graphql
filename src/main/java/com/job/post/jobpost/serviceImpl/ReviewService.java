package com.job.post.jobpost.serviceImpl;

import com.job.post.jobpost.models.Review;
import com.job.post.jobpost.repositories.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Optional<Review> findById(Long reviewId) {
        return reviewRepository.findById(reviewId);
    }

    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    public void deleteById(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
