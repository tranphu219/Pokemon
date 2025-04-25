package com.example.pokemon.Config;

import com.example.pokemon.Service.Impl.Custom_Admin_User_DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private Custom_Admin_User_DetailsService customUserDetailsService;

    // Cấu hình bảo mật cho Admin
    @Bean
    @Order(1)
    public SecurityFilterChain adminSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/admin/**", "/home/**") // Thêm /home vào đây
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/login", "/home/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/admin/login")
                        .loginProcessingUrl("/admin/login")
                        .defaultSuccessUrl("/admin/dashboard", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/admin/logout")
                        .logoutSuccessUrl("/admin/login?logout")
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable()); // Tạm thời disable CSRF để test

        return http.build();
    }

    // Cấu hình bảo mật cho User
    @Bean
    @Order(2)
    public SecurityFilterChain userSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/user/**")
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/login", "/user/register").permitAll()
                        .requestMatchers("/user/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/user/login")
                        .loginProcessingUrl("/user/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/home", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/user/logout")
                        .logoutSuccessUrl("/user/login?logout")
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}