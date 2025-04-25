package com.example.pokemon.Service.Impl;

import com.example.pokemon.Model.OrdersEntity;
import com.example.pokemon.Model.UsersEntity;
import com.example.pokemon.Repository.OrdersRepository;
import com.example.pokemon.Service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public List<OrdersEntity> findAll() {
        return ordersRepository.findAll();
    }

    @Override
    public OrdersEntity findById(int id) {
        return ordersRepository.findById(id).get();
    }

    @Override
    public OrdersEntity save(OrdersEntity order) {
        return ordersRepository.save(order);
    }

    @Override
    public OrdersEntity update(int id) {
        return ordersRepository.findById(id).get();
    }

    @Override
    public void delete(int id) {
        ordersRepository.deleteById(id);
    }

    @Override
    public void updateStatus(int orderId, int status) {
        updateStatus(orderId, status);
    }

    @Override
    public Object getAllOrders() {
        return ordersRepository.findAll();
    }

    @Override
    public Page<OrdersEntity> findAll(Pageable pageable) {
        return ordersRepository.findAll(pageable);
    }
}
