package com.example.pokemon.Service;

import com.example.pokemon.Model.OrdersEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrdersService {
    List<OrdersEntity> findAll();
    OrdersEntity findById(int id);
    OrdersEntity save(OrdersEntity order);
    OrdersEntity update(int id);
    void delete(int id);

//    them
    void updateStatus(int orderId, int status);
    Page<OrdersEntity> findAll(Pageable pageable); // Thêm dòng này
    Object getAllOrders();
}
