package com.job.post.jobpost.repositories;

import com.job.post.jobpost.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
