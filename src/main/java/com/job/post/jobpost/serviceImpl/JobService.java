package com.job.post.jobpost.serviceImpl;

import com.job.post.jobpost.models.Job;
import com.job.post.jobpost.repositories.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class JobService {


    private JobRepository jobRepository;

    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    public Job findById(Long jobId) {
        try {
            return jobRepository.findById(jobId).orElseThrow(() -> new Exception("Job not found"));
        } catch (Exception e) {
            return null;
        }
    }

    public Job save(Job job) {
        return jobRepository.save(job);
    }

    public void deleteById(Long jobId) {
        jobRepository.deleteById(jobId);
    }
}
