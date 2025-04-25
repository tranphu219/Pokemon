package com.example.pokemon.Service;

import com.example.pokemon.Model.AdminEntity;

public interface AdminService {
    AdminEntity findByUsername(String username);
}
