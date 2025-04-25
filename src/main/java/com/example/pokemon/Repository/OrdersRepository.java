package com.example.pokemon.Repository;

import com.example.pokemon.Model.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity, Integer>, JpaSpecificationExecutor<OrdersEntity> {
}
