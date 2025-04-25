package com.example.pokemon.Service.Impl;

import com.example.pokemon.Model.Order_itemsEntity;
import com.example.pokemon.Repository.Order_itemsRepository;
import com.example.pokemon.Service.Orders_itemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Orders_itemsServiceImpl implements Orders_itemsService {
    @Autowired
    private Order_itemsRepository ordersRepository;

    @Override
    public List<Order_itemsEntity> findAll() {
        return ordersRepository.findAll();
    }

    @Override
    public Order_itemsEntity findById(int id) {
        return ordersRepository.findById(id).get();
    }

    @Override
    public Order_itemsEntity save(Order_itemsEntity orderItemsEntity) {
        return ordersRepository.save(orderItemsEntity);
    }

    @Override
    public Order_itemsEntity update(int id) {
        return ordersRepository.findById(id).get();
    }

    @Override
    public void delete(int id) {
        ordersRepository.deleteById(id);
    }
}
