package com.example.pokemon.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catId")
    private int categoryId;
    @Column(name = "name")
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "category")
    private List<ProductEntity> products;

    public CategoryEntity() {
    }

}
