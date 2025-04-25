package com.example.pokemon.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Order_items")
public class Order_itemsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private int order_item_id;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")
    private double price;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "orderId")
    private OrdersEntity ordersEntity;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "proId", referencedColumnName = "proId")
    private ProductEntity product;
}
