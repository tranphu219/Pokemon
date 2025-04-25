package com.example.pokemon.Repository;

import com.example.pokemon.Model.Order_itemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface Order_itemsRepository extends JpaRepository<Order_itemsEntity, Integer>, JpaSpecificationExecutor<Order_itemsEntity> {
}
