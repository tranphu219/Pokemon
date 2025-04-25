package com.example.pokemon.Service.Impl;

import com.example.pokemon.Model.AdminEntity;
import com.example.pokemon.Repository.AdminRepository;
import com.example.pokemon.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public AdminEntity findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }
}
