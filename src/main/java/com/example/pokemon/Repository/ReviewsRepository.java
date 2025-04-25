package com.example.pokemon.Repository;

import com.example.pokemon.Model.ReviewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewsRepository extends JpaRepository<ReviewsEntity, Integer>, JpaSpecificationExecutor<ReviewsEntity> {
}
