package com.example.pokemon.Service;

import com.example.pokemon.Model.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<ProductEntity> findAll();
    ProductEntity findById(int id);
    ProductEntity save(ProductEntity product);
    ProductEntity update(int id);
    void delete(int id);


    // Bổ sung thêm
    long countTotalProducts();
    long countProductsLowStock(int threshold);
    List<ProductEntity> findProductsLowStock(int threshold);
    Page<ProductEntity> findAll(Pageable pageable); // Thêm dòng này

}
