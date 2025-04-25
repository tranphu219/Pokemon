package com.example.pokemon.Repository;

import com.example.pokemon.Model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Integer>{
    UsersEntity findByname(String name);
    UsersEntity findByemail(String email);
}
