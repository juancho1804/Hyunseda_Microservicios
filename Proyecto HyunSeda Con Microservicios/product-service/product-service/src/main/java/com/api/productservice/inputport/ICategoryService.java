package com.api.productservice.inputport;

import com.api.productservice.exceptions.ProductDomainException;
import com.api.productservice.exceptions.ResourceNotFoundException;
import com.api.productservice.models.CategoryModel;

import java.util.ArrayList;
import java.util.List;

public interface ICategoryService {
    ArrayList<CategoryModel> getCategories();
    CategoryModel save(CategoryModel newCategoryModel)throws ProductDomainException;
    CategoryModel findById(Long id)throws ResourceNotFoundException;
    CategoryModel findByName(String name);
    CategoryModel updateById(CategoryModel categoryModel,long id)throws ProductDomainException,ResourceNotFoundException;
    boolean deleteById(Long id) throws ResourceNotFoundException;
    List<CategoryModel> findByMatchingName(String name);
    List<CategoryModel> findByMatchingId(String id);
}
