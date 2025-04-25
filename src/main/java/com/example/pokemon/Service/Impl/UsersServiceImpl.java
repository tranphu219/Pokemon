package com.example.pokemon.Service.Impl;

import com.example.pokemon.Model.UsersEntity;
import com.example.pokemon.Repository.UsersRepository;
import com.example.pokemon.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;
    @Override
    public UsersEntity findByname(String name) {
        return usersRepository.findByname(name);
    }

    @Override
    public List<UsersEntity> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public UsersEntity findByid(int id) {
        return usersRepository.findById(id).get();
    }

    @Override
    public UsersEntity save(UsersEntity usersEntity) {
        return usersRepository.save(usersEntity);
    }

    @Override
    public UsersEntity update(int id) {
        return usersRepository.findById(id).get();
    }

//    them
    @Override
    public Page<UsersEntity> findAll(Pageable pageable) {
        return usersRepository.findAll(pageable);
    }

    @Override
    public Object getAllUsers() {
        return usersRepository.findAll();
    }
}
