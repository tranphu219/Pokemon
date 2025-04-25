package com.example.pokemon.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proId")
    private int proId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private double price;
    @Column(name = "color")
    private String color;
    @Column(name = "size")
    private String size;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "image")
    private String image;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "catId", referencedColumnName = "catId")
    private CategoryEntity category;


    @JsonManagedReference
    @OneToMany(mappedBy = "product")
    private List<ReviewsEntity> reviewsEntities;

    @JsonManagedReference
    @OneToMany(mappedBy = "product")
    private List<Order_itemsEntity>  orderItemsEntities;


    @JsonManagedReference
    @OneToMany(mappedBy = "product")
    private List<Cart_itemsEntity> cartItemsEntities;



    public ProductEntity() {
    }


}
