package com.job.post.jobpost.serviceImpl;

import com.job.post.jobpost.models.Bid;
import com.job.post.jobpost.repositories.BidRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BidService {


    private BidRepository bidRepository;

    public List<Bid> findAll() {
        return bidRepository.findAll();
    }

    public Optional<Bid> findById(Long bidId) {
        return bidRepository.findById(bidId);
    }

    public Bid save(Bid bid) {
        return bidRepository.save(bid);
    }

    public void deleteById(Long bidId) {
        bidRepository.deleteById(bidId);
    }
}

