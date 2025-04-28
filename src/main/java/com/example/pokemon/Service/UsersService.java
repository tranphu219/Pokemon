package com.example.pokemon.Service;

import com.example.pokemon.Model.UsersEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UsersService {
    UsersEntity findByname(String name);
    List<UsersEntity> findAll();
    UsersEntity findByid(int id);
    UsersEntity save(UsersEntity usersEntity);
    UsersEntity update(int id);

    UsersEntity findByemail(String email);

    //    them
    Page<UsersEntity> findAll(Pageable pageable);
    Object getAllUsers();
}
