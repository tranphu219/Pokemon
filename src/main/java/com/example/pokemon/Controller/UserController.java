package com.example.pokemon.Controller;

import com.example.pokemon.Model.UsersEntity;
import com.example.pokemon.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("usersEntity", new UsersEntity());
        return "user/register";
    }
    @PostMapping("/registerSave")
    public String registerSave(@ModelAttribute UsersEntity usersEntity) {
        // Gán mật khẩu đã mã hóa
        String encodedPassword = passwordEncoder.encode(usersEntity.getPassword());
        usersEntity.setPassword(encodedPassword);
        usersService.save(usersEntity);
        return "redirect:/user/login";
    }
}
