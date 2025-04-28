package com.example.pokemon.Controller;

import com.example.pokemon.Model.ProductEntity;
import com.example.pokemon.Service.CategoryService;
import com.example.pokemon.Service.ProductService;
import com.example.pokemon.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
public class HomeController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UsersService usersService;

    @GetMapping("/home")
    public String home(Model model) {
        List<ProductEntity> lowQuantityProducts = productService.findTop6ProductsLowQuantity(50);
        model.addAttribute("lowQuantityProducts", lowQuantityProducts);
        return "home";
    }


}
