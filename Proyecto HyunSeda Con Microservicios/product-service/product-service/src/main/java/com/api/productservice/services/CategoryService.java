package com.api.productservice.services;

import com.api.productservice.models.CategoryModel;
import com.api.productservice.repositories.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    ICategoryRepository categoryRepository;


    @Override
    public ArrayList<CategoryModel> getCategories() {
        return (ArrayList<CategoryModel>) this.categoryRepository.findAll();
    }

    @Override
    public CategoryModel save(CategoryModel newCategoryModel) {
        return categoryRepository.save(newCategoryModel);
    }

    @Override
    public Optional<CategoryModel> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public ArrayList<CategoryModel> findByName(String name) {
        ArrayList<CategoryModel> nombresCategoria = new ArrayList<>();
        for(CategoryModel category : this.categoryRepository.findAll()) {
            if(category.getName().equals(name)) {
                nombresCategoria.add(category);
            }
        }
        return nombresCategoria;
    }

    @Override
    public CategoryModel updateById(CategoryModel categoryModel, long id) {
        CategoryModel categoria = categoryRepository.findById(id).get();
        categoria.setName(categoryModel.getName());
        categoryRepository.save(categoria);
        return categoria;
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            categoryRepository.deleteById(id);
        }catch (Exception e) {
            return false;
        }
        return true;
    }
}
