package com.example.pokemon.Service;

import com.example.pokemon.Model.ReviewsEntity;

import java.util.List;

public interface ReviewsService {
    List<ReviewsEntity> findAll();
    ReviewsEntity findById(int id);
    ReviewsEntity save(ReviewsEntity reviewsEntity);
    void delete(int id );
}
