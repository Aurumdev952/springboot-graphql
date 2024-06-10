package com.job.post.jobpost.controllers;

import com.job.post.jobpost.dtos.CreateBidInput;
import com.job.post.jobpost.dtos.UpdateBidInput;
import com.job.post.jobpost.models.Bid;
import com.job.post.jobpost.models.Job;
import com.job.post.jobpost.models.User;
import com.job.post.jobpost.serviceImpl.BidService;
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

@Controller
@AllArgsConstructor
public class BidController {

    private final BidService bidService;
    private final JobService jobService;
    private final AuthUtil authUtil;

    @QueryMapping
    public List<Bid> bids() {
        return bidService.findAll();
    }


    @QueryMapping
    public Bid bid(@Argument long id) {
        try {
            return bidService.findById(id).orElseThrow(() -> new Exception("Bid not found"));
        } catch (Exception e) {
            return null;
        }
    }

    @SchemaMapping
    public Job job(Bid bid) {
        return bid.getJob();
    }

    @SchemaMapping
    public User user(Bid bid) {
        return bid.getUser();
    }


    @MutationMapping
    public boolean deleteBid(@Argument long id) {
        bidService.deleteById(id);
        return true;
    }
    @MutationMapping
    public Bid createBid(@Argument CreateBidInput input) throws Exception {
        Bid bid = new Bid();
        User user = authUtil.getCurrentUser();
        if (user == null) {
            throw new Exception("User not found");
        }
        Job job = jobService.findById((long) input.jobId());
        if (job == null) {
            throw new Exception("Job not found");
        }
        bid.setJob(job);
        bid.setMessage(input.message());
        bid.setOfferPrice(input.offerPrice());
        bid.setStatus(input.status());
        bid.setUser(user);
        bidService.save(bid);
        return bid;
    }
    @MutationMapping
    public Bid updateBid(@Argument long id, @Argument UpdateBidInput input) throws Exception {
        Bid bid = bidService.findById(id).orElseThrow(() -> new Exception(("bid not found")));
        User user = authUtil.getCurrentUser();
        if (user == null) {
            throw new Exception("User not found");
        }
        Job job = jobService.findById((long) input.jobId());
        if (job == null) {
            throw new Exception("Job not found");
        }
        bid.setJob(job);
        bid.setMessage(input.message());
        bid.setOfferPrice(input.offerPrice());
        bid.setStatus(input.status());
        bid.setUser(user);
        bidService.save(bid);
        return bid;
    }


}
