package com.example.pokemon.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Users")
public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "phone")
    private String phone;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @JsonManagedReference
    @OneToMany(mappedBy = "userEntity")
    private List<OrdersEntity> ordersEntity;


    @JsonManagedReference
    @OneToMany(mappedBy = "userEntity")
    private List<Cart_itemsEntity>  cart_itemsEntities;

    @JsonManagedReference
    @OneToMany(mappedBy = "userEntity")
    private List<ReviewsEntity>  reviewsEntities ;


    public UsersEntity() {
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<OrdersEntity> getOrdersEntity() {
        return ordersEntity;
    }

    public void setOrdersEntity(List<OrdersEntity> ordersEntity) {
        this.ordersEntity = ordersEntity;
    }

    public List<Cart_itemsEntity> getCart_itemsEntities() {
        return cart_itemsEntities;
    }

    public void setCart_itemsEntities(List<Cart_itemsEntity> cart_itemsEntities) {
        this.cart_itemsEntities = cart_itemsEntities;
    }

    public List<ReviewsEntity> getReviewsEntities() {
        return reviewsEntities;
    }

    public void setReviewsEntities(List<ReviewsEntity> reviewsEntities) {
        this.reviewsEntities = reviewsEntities;
    }
}
