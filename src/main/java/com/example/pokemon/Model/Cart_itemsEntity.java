package com.example.pokemon.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


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

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public UsersEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UsersEntity userEntity) {
        this.userEntity = userEntity;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}
