package com.example.pokemon.Service;


import com.example.pokemon.Model.Order_itemsEntity;

import java.util.List;

public interface Orders_itemsService {
    List<Order_itemsEntity> findAll();
    Order_itemsEntity findById(int id);
    Order_itemsEntity save(Order_itemsEntity orderItemsEntity);
    Order_itemsEntity update(int id); // có thể thêm đối số
    void delete(int id);
}