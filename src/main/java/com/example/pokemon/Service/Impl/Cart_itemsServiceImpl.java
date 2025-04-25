package com.example.pokemon.Service.Impl;

import com.example.pokemon.Model.Cart_itemsEntity;
import com.example.pokemon.Repository.Cart_itemsRepository;
import com.example.pokemon.Service.Cart_itemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Cart_itemsServiceImpl implements Cart_itemsService {
    @Autowired
    private Cart_itemsRepository cart_itemsRepository;

    @Override
    public List<Cart_itemsEntity> findAll() {
        return cart_itemsRepository.findAll();
    }

    @Override
    public Cart_itemsEntity findById(int id) {
        return cart_itemsRepository.findById(id).get();
    }

    @Override
    public Cart_itemsEntity save(Cart_itemsEntity cart_itemsEntity) {
        return cart_itemsRepository.save(cart_itemsEntity);
    }

    @Override
    public Cart_itemsEntity update(int id) {
        return cart_itemsRepository.findById(id).get();
    }

    @Override
    public void delete(int id) {
        cart_itemsRepository.deleteById(id);
    }
}
