package com.example.pokemon.Service.Impl;

import com.example.pokemon.Model.ProductEntity;
import com.example.pokemon.Repository.ProductRepository;
import com.example.pokemon.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    @Override
    public ProductEntity findById(int id) {
        return productRepository.findById(id).get();
    }

    @Override
    public ProductEntity save(ProductEntity product) {
        return productRepository.save(product);
    }

    @Override
    public ProductEntity update(int id) {
        return productRepository.findById(id).get();
    }

    @Override
    public void delete(int id) {
        productRepository.deleteById(id);
    }



//    bổ xung thêm
@Override
public long countTotalProducts() {
    return productRepository.count();
}

    @Override
    public long countProductsLowStock(int threshold) {
        return productRepository.countByQuantityLessThan(threshold);
    }

    @Override
    public List<ProductEntity> findProductsLowStock(int threshold) {
        return productRepository.findByQuantityLessThan(threshold);
    }

    @Override
    public Page<ProductEntity> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public List<ProductEntity> findTop6ProductsLowQuantity(int maxQuantity) {
        return productRepository.findTop6ByQuantityLessThanEqualOrderByQuantityAsc(maxQuantity);
    }
}
