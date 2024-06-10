package com.job.post.jobpost.controllers;

import com.job.post.jobpost.dtos.CreateJobInput;
import com.job.post.jobpost.dtos.UpdateJobInput;
import com.job.post.jobpost.models.Job;
import com.job.post.jobpost.models.User;
import com.job.post.jobpost.serviceImpl.JobService;
import com.job.post.jobpost.utils.AuthUtil;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;


@AllArgsConstructor
@Controller
public class JobController {
    private final JobService jobService;
    private final AuthUtil authUtil;
    @QueryMapping
    public Job job(@Argument long id) {
        return jobService.findById(id);
    }

    @QueryMapping
    public List<Job> jobs() {
        return jobService.findAll();
    }

    @SchemaMapping
    public User user(Job job) {
        return job.getUser();
    }

    @MutationMapping
    public Job createJob(@Argument CreateJobInput input) throws Exception {
        User user = authUtil.getCurrentUser();
        Job job = new Job();
        job.setUser(user);
        job.setCategory(input.category());
        job.setPrice(input.price());
        job.setDescription(input.description());
        job.setTitle(input.title());
        job.setStatus(input.status());
        jobService.save(job);
        return job;
    }

    @MutationMapping
    public Job updateJob(@Argument long id, @Argument UpdateJobInput input) throws Exception {
        Job job = jobService.findById(id);
        if (job == null) {
            throw new Exception("Job not found");
        }
        job.setCategory(input.category());
        job.setPrice(input.price());
        job.setDescription(input.description());
        job.setTitle(input.title());
        job.setStatus(input.status());
        jobService.save(job);
        return job;
    }

    public boolean deleteJob(@Argument int id) {
        jobService.deleteById((long) id);
        return true;
    }

}
