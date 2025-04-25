package com.example.pokemon.Repository;

import com.example.pokemon.Model.Cart_itemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface Cart_itemsRepository extends JpaRepository<Cart_itemsEntity, Integer>, JpaSpecificationExecutor<Cart_itemsEntity> {
}
