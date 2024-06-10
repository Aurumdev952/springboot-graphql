package com.job.post.jobpost.repositories;

import com.job.post.jobpost.models.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<Bid, Long> {
}
