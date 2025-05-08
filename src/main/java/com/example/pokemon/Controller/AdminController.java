package com.example.pokemon.Controller;

import com.example.pokemon.Model.CategoryEntity;
import com.example.pokemon.Model.OrdersEntity;
import com.example.pokemon.Model.ProductEntity;
import com.example.pokemon.Model.UsersEntity;
import com.example.pokemon.Repository.CategoryRepository;
import com.example.pokemon.Repository.ProductRepository;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductRepository productRepository;

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
        return "admin/Products";
    }

    @GetMapping("/products/add")
    public String showAddProductForm(Model model) {
        // Retrieve the list of categories from the database
        List<CategoryEntity> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("product", new ProductEntity());
        model.addAttribute("allColors", Arrays.asList("Red", "Blue", "Green", "Black", "White", "Yellow")); // Danh sách màu có sẵn
        return "admin/product-form";
    }

    @PostMapping("/products/save")
    public String saveProduct(@ModelAttribute ProductEntity product,
                              @RequestParam("sizes") List<String> sizes,
                              @RequestParam("colors") String colors,
                              @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        // Xử lý các size
        String sizeString = String.join(",", sizes);
        product.setSize(sizeString);
        // Xử lý sác màu sắc
        product.setColor(colors);
        // Xử lý ảnh
        if (!imageFile.isEmpty()) {
            String imageFileName = imageFile.getOriginalFilename();
            String uploadDir = "uploads/";
            Path uploadPath = Paths.get(uploadDir);
            // Kiểm tra và tạo thư mục nếu chưa tồn tại
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath); // Tự động tạo thư mục nếu nó chưa tồn tại
            }
            // Lưu file vào thư mục
            Path filePath = uploadPath.resolve(imageFileName);
            Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            // Lưu tên file vào thuộc tính của ProductEntity
            product.setImage(imageFileName);
        }
        // Lưu sản phẩm vào cơ sở dữ liệu
        productRepository.save(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/product/xoa/{id}")
    public String showProductXOAForm(@PathVariable("id") int id) {
        productService.delete(id);
        return "redirect:/admin/products";
    }



    @GetMapping("/products/ProUpdate/{id}")
    public String productsUp(Model model, @PathVariable int id) {
        ProductEntity product = productService.findById(id);
        List<CategoryEntity> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("product", product);
        return "admin/ProUpdate";
    }

    @PostMapping("/products/edit")
    public String updateProducts(@ModelAttribute ProductEntity productEntity,
                                 @RequestParam("sizes") List<String> sizes,
                                 @RequestParam("imageFile") MultipartFile imageFile) throws IOException {

        // Xử lý các size
        String sizeString = String.join(",", sizes);
        productEntity.setSize(sizeString);  // Lưu danh sách size thành chuỗi

        // Xử lý ảnh
        if (!imageFile.isEmpty()) {
            String imageFileName = imageFile.getOriginalFilename();
            String uploadDir = "uploads/";
            Path uploadPath = Paths.get(uploadDir);

            // Kiểm tra và tạo thư mục nếu chưa tồn tại
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Lưu file vào thư mục
            Path filePath = uploadPath.resolve(imageFileName);
            Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Lưu tên file vào thuộc tính của ProductEntity
            productEntity.setImage(imageFileName);
        }

        // Cập nhật thông tin sản phẩm
        productService.update(productEntity.getProId(), productEntity);

        return "redirect:/admin/products";
    }


//     size and img


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
    @PostMapping("/categories/save")
    public String saveCategory(@ModelAttribute CategoryEntity category) {
        categoryService.save(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories/sua/{id}")
    public String categoriessua(Model model, @PathVariable int id) {
        CategoryEntity category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "admin/category-edit";
    }

    @PostMapping("/categories/edit")
    public String updateCategory(@ModelAttribute CategoryEntity category) {
        categoryService.update(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable("id") int id) {
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
        return "admin/users";    }
    }
