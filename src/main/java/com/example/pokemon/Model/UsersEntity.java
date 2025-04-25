package com.example.pokemon.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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

}
