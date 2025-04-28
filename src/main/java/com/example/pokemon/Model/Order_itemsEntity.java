package com.example.pokemon.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


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

    public Order_itemsEntity() {
    }

    public int getOrder_item_id() {
        return order_item_id;
    }

    public void setOrder_item_id(int order_item_id) {
        this.order_item_id = order_item_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public OrdersEntity getOrdersEntity() {
        return ordersEntity;
    }

    public void setOrdersEntity(OrdersEntity ordersEntity) {
        this.ordersEntity = ordersEntity;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}
