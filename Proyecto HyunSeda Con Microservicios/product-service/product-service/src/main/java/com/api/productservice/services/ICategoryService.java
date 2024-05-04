package com.api.productservice.services;

import com.api.productservice.exceptions.ProductDomainException;
import com.api.productservice.exceptions.ResourceNotFoundException;
import com.api.productservice.models.CategoryModel;

import java.util.ArrayList;
import java.util.Optional;

public interface ICategoryService {
    public ArrayList<CategoryModel> getCategories();
    public CategoryModel save(CategoryModel newCategoryModel)throws ProductDomainException;
    public CategoryModel findById(Long id)throws ResourceNotFoundException;
    public CategoryModel findByName(String name);
    public CategoryModel updateById(CategoryModel categoryModel,long id)throws ProductDomainException,ResourceNotFoundException;
    public boolean deleteById(Long id) throws ResourceNotFoundException;
}