package com.example.pokemon.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Orders")
public class OrdersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private int orderId;
    @Column(name = "total_amount")
    private Double totalAmount;
    @Column(name = "status")
    private int status;
    @UpdateTimestamp
    @Column(name = "created_at")
    private LocalDateTime updatedAt;

    @JsonManagedReference
    @OneToMany(mappedBy = "ordersEntity")
    private List<Order_itemsEntity> order_items;


    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private UsersEntity userEntity;

    public OrdersEntity() {
    }

}
