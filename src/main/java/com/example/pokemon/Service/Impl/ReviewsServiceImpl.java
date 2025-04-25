package com.example.pokemon.Service.Impl;

import com.example.pokemon.Model.ReviewsEntity;
import com.example.pokemon.Repository.ReviewsRepository;
import com.example.pokemon.Service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewsServiceImpl implements ReviewsService {
    @Autowired
    private ReviewsRepository reviewsRepository;

    @Override
    public List<ReviewsEntity> findAll() {
        return reviewsRepository.findAll();
    }

    @Override
    public ReviewsEntity findById(int id) {
        return reviewsRepository.findById(id).get();
    }

    @Override
    public ReviewsEntity save(ReviewsEntity reviewsEntity) {
        return reviewsRepository.save(reviewsEntity);
    }

    @Override
    public void delete(int id) {
        reviewsRepository.deleteById(id);
    }
}
