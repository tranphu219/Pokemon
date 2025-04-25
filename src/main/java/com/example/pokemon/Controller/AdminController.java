package com.example.pokemon.Controller;

import com.example.pokemon.Model.CategoryEntity;
import com.example.pokemon.Model.OrdersEntity;
import com.example.pokemon.Model.ProductEntity;
import com.example.pokemon.Model.UsersEntity;
import com.example.pokemon.Service.CategoryService;
import com.example.pokemon.Service.OrdersService;
import com.example.pokemon.Service.ProductService;
import com.example.pokemon.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalProducts", productService.countTotalProducts());
        model.addAttribute("lowStockProducts", productService.countProductsLowStock(5));
        model.addAttribute("totalUsers", usersService.findAll().size());
        model.addAttribute("totalOrders", ordersService.findAll().size());
        return "admin/dashboard";
    }

    // ========== PRODUCT MANAGEMENT ==========
    @GetMapping("/products")
    public String productManagement(Model model,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("proId").descending());
        Page<ProductEntity> productPage = productService.findAll(pageable);

        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        return "admin/products";
    }

    @GetMapping("/products/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new ProductEntity());
        model.addAttribute("categories", categoryService.findAll());
        return "admin/product-form";
    }

    @PostMapping("/products/save")
    public String saveProduct(@ModelAttribute ProductEntity product,
                              @RequestParam("imageFile") MultipartFile imageFile) {
        productService.save(product);
        return "redirect:/admin/products";
    }

    // ========== CATEGORY MANAGEMENT ==========

    @GetMapping("/categories")
    public String categoryManagement(Model model,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("categoryId").descending());
        Page<CategoryEntity> categoryPage = categoryService.findAll(pageable);

        model.addAttribute("categories", categoryPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", categoryPage.getTotalPages());
        model.addAttribute("pageTitle", "Categories");
        return "admin/categories";
    }

    @GetMapping("/categories/add")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new CategoryEntity());
        return "admin/category-form";
    }

    @GetMapping("/categories/edit/{id}")
    public String showEditCategoryForm(@PathVariable("id") Integer id, Model model) {
        CategoryEntity category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "admin/category-form";
    }

    @PostMapping("/categories/save")
    public String saveCategory(@ModelAttribute CategoryEntity category) {
        categoryService.save(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable("id") Integer id) {
        categoryService.deleteById(id);
        return "redirect:/admin/categories";
    }

    // ========== ORDER MANAGEMENT ==========
    @GetMapping("/orders")
    public String orderManagement(Model model,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("orderId").descending());
        Page<OrdersEntity> orderPage = ordersService.findAll(pageable);

        model.addAttribute("orders", orderPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", orderPage.getTotalPages());
        model.addAttribute("pageTitle", "Orders");
        return "admin/orders";
    }

    @GetMapping("/orders/{id}")
    public String viewOrderDetail(@PathVariable int id, Model model) {
        OrdersEntity order = ordersService.findById(id);
        model.addAttribute("order", order);
        return "admin/order-detail";
    }

    @PostMapping("/orders/update-status")
    public String updateOrderStatus(@RequestParam int orderId,
                                    @RequestParam int status) {
        ordersService.updateStatus(orderId, status);
        return "redirect:/admin/orders";
    }

    // ========== USER MANAGEMENT ==========
    @GetMapping("/users")
    public String userManagement(Model model,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<UsersEntity> userPage = usersService.findAll(pageable);

        model.addAttribute("users", userPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userPage.getTotalPages());
        model.addAttribute("pageTitle", "Users");
        return "admin/users";
    }
}