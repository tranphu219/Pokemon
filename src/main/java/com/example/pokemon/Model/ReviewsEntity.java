package com.example.pokemon.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Reviews")
public class ReviewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private int reviewId;

    @Column(name = "rating")
    private int rating;
    @Column(name = "comment")
    private String comment;

    @UpdateTimestamp
    @Column(name = "created_at")
    private LocalDateTime updatedAt;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private UsersEntity userEntity;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "proId", referencedColumnName = "proId")
    private ProductEntity product;

    public ReviewsEntity() {
    }
}
