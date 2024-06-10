package com.api.productservice.inputport;

import com.api.productservice.domain.Category;
import com.api.productservice.exceptions.ProductDomainException;
import com.api.productservice.exceptions.ResourceNotFoundException;
import com.api.productservice.models.CategoryModel;

import java.util.ArrayList;
import java.util.List;

public interface ICategoryService {
    ArrayList<Category> getCategories();
    Category save(Category newCategory)throws ProductDomainException;
    Category findById(Long id)throws ResourceNotFoundException;
    Category findByName(String name);
    Category updateById(Category category,long id)throws ProductDomainException,ResourceNotFoundException;
    boolean deleteById(Long id) throws ResourceNotFoundException;
    List<Category> findByMatchingName(String name);
    List<Category> findByMatchingId(String id);
}
