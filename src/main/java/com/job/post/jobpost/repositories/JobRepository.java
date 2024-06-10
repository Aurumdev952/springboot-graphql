package com.job.post.jobpost.repositories;

import com.job.post.jobpost.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
