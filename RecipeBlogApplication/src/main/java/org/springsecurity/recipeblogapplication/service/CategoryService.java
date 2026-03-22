package org.springsecurity.recipeblogapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springsecurity.recipeblogapplication.entity.Category;
import org.springsecurity.recipeblogapplication.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(int id){
        return categoryRepository.findById(id);
    }

    public Category save(Category category){
        return categoryRepository.save(category);
    }

    public void deleteById(int id){
        categoryRepository.deleteById(id);
    }

    public boolean existsByName(String name){
        return categoryRepository.existsByName(name);
    }
}
