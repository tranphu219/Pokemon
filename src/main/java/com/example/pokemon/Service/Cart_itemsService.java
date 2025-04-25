package com.example.pokemon.Service;

import com.example.pokemon.Model.Cart_itemsEntity;

import java.util.List;

public interface Cart_itemsService {
    List<Cart_itemsEntity> findAll();
    Cart_itemsEntity findById(int id);
    Cart_itemsEntity save(Cart_itemsEntity cart_itemsEntity);
    Cart_itemsEntity update(int id);
    void delete(int id );
}
