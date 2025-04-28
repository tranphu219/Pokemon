package com.example.pokemon.Service.Impl;

import com.example.pokemon.Model.CategoryEntity;
import com.example.pokemon.Repository.CategoryRepository;
import com.example.pokemon.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryEntity findById(int id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public CategoryEntity save(CategoryEntity category) {
        return categoryRepository.save(category);
    }

    @Override
    public CategoryEntity update(CategoryEntity category) {
        return categoryRepository.save(category); // Spring Data sẽ auto update nếu có ID
    }



    @Override
    public void delete(int id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Page<CategoryEntity> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public void deleteById(int id) {
        categoryRepository.deleteById(id);
    }
}
