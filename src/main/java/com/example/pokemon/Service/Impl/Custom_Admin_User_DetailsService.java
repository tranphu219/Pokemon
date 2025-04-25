package com.example.pokemon.Service.Impl;

import com.example.pokemon.Model.AdminEntity;
import com.example.pokemon.Model.UsersEntity;
import com.example.pokemon.Repository.AdminRepository;
import com.example.pokemon.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class Custom_Admin_User_DetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {
        // Check admin first
        AdminEntity admin = adminRepository.findByUsername(input);
        if (admin != null) {
            return User.builder()
                    .username(admin.getUsername())
                    .password(admin.getPassword())
                    .roles(admin.getRoles().toUpperCase()) // Đảm bảo role là chữ hoa
                    .build();
        }

        // Check user
        UsersEntity user = usersRepository.findByemail(input); // Đảm bảo tên method đúng
        if (user != null) {
            return User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .roles("USER")
                    .build();
        }
        throw new UsernameNotFoundException("Tài khoản không tồn tại: " + input);
    }
}