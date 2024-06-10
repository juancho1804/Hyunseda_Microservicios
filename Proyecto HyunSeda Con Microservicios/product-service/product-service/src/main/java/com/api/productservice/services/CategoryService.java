package com.api.productservice.services;

import com.api.productservice.domain.Category;
import com.api.productservice.exceptions.EnumErrorCodes;
import com.api.productservice.exceptions.ProductDomainException;
import com.api.productservice.exceptions.ProductError;
import com.api.productservice.exceptions.ResourceNotFoundException;
import com.api.productservice.inputport.ICategoryService;
import com.api.productservice.mappers.CategoryMapper;
import com.api.productservice.models.CategoryModel;
import com.api.productservice.outputport.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    ICategoryRepository categoryRepository;


    @Override
    public ArrayList<Category> getCategories() {
        List<CategoryModel> categoryModels = categoryRepository.findAll();
        ArrayList<Category> categories = new ArrayList<>();
        for (CategoryModel categoryModel : categoryModels) {
            categories.add(CategoryMapper.toDomain(categoryModel));
        }
        return categories;
    }

    @Override
    public Category save(Category newCategory) throws ProductDomainException {
        List<ProductError> errors = validateDomain(newCategory);
        if (!errors.isEmpty()) {
            throw new ProductDomainException(errors);
        }
        CategoryModel categoryModel = CategoryMapper.toEntity(newCategory);
        return CategoryMapper.toDomain(categoryRepository.save(categoryModel));
    }

    @Override
    public Category findById(Long id) throws ResourceNotFoundException {
        CategoryModel category = this.categoryRepository.findById(id).orElse(null);
        if (category == null) {
            throw new ResourceNotFoundException();
        }
        return CategoryMapper.toDomain(category);
    }

    @Override
    public Category findByName(String name) {
        for (CategoryModel categoryModel : categoryRepository.findAll()) {
            if (categoryModel.getName().equals(name)) {
                return CategoryMapper.toDomain(categoryModel);
            }
        }
        return null;
    }

    @Override
    public Category updateById(Category newCategory, long id) throws ProductDomainException, ResourceNotFoundException {
        CategoryModel category = this.categoryRepository.findById(id).orElse(null);
        if (category == null) {
            throw new ResourceNotFoundException();
        }
        List<ProductError> errors = validateDomain(newCategory);
        if (!errors.isEmpty()) {
            throw new ProductDomainException(errors);
        }
        category.setName(newCategory.getName());
        categoryRepository.save(category);
        return CategoryMapper.toDomain(category);
    }

    @Override
    public boolean deleteById(Long id) throws ResourceNotFoundException {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException();
        }
        categoryRepository.deleteById(id);
        return true;
    }

    public List<Category> findByMatchingName(String name) {
        List<CategoryModel> categoryModels = categoryRepository.findByMatchingName(name);
        List<Category> categories = new ArrayList<>();
        for (CategoryModel categoryModel : categoryModels) {
            categories.add(CategoryMapper.toDomain(categoryModel));
        }
        return categories;
    }
    public List<Category> findByMatchingId(String id) {
        List<CategoryModel> categoryModels = categoryRepository.findByMatchingId(id);
        List<Category> categories = new ArrayList<>();
        for (CategoryModel categoryModel : categoryModels) {
            categories.add(CategoryMapper.toDomain(categoryModel));
        }
        return categories;
    }

    private List<ProductError> validateDomain(Category category) {
        List<ProductError> errors = new ArrayList<>();
        if (category.getName() == null || category.getName().isBlank()) {
            errors.add(new ProductError(EnumErrorCodes.EMPTY_FIELD, "name", "El nombre de la categoría es obligatorio"));
        }
        return errors;
    }
}
