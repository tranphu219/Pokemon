package com.example.pokemon.Service;

import com.example.pokemon.Model.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    List<CategoryEntity> findAll();
    CategoryEntity findById(int id);
    CategoryEntity save(CategoryEntity category);
    CategoryEntity update(int id);
    void delete(int id);
    Page<CategoryEntity> findAll(Pageable pageable);

    void deleteById(int id);
}
