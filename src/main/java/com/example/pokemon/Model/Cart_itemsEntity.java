package com.example.pokemon.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Cart_items")
public class Cart_itemsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartId")
    private int cartId;
    @Column(name = "quantity")
    private int quantity;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private UsersEntity userEntity;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "proId", referencedColumnName = "proId")
    private ProductEntity product;

    public Cart_itemsEntity() {
    }


}
