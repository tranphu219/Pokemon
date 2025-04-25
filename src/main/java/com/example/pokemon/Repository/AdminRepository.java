package com.example.pokemon.Repository;

import com.example.pokemon.Model.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {
    AdminEntity findByUsername (String username);
}
