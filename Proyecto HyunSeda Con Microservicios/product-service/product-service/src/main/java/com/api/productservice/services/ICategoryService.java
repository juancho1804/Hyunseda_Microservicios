package com.api.productservice.services;

import com.api.productservice.models.CategoryModel;

import java.util.ArrayList;
import java.util.Optional;

public interface ICategoryService {
    public ArrayList<CategoryModel> getCategories();
    public CategoryModel save(CategoryModel newCategoryModel);
    public Optional<CategoryModel> findById(Long id);
    public ArrayList<CategoryModel> findByName(String name);
    public CategoryModel updateById(CategoryModel categoryModel,long id);
    public boolean deleteById(Long id);
}
