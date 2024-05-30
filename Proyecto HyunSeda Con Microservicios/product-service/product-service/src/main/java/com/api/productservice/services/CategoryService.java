package com.api.productservice.services;

import com.api.productservice.exceptions.EnumErrorCodes;
import com.api.productservice.exceptions.ProductDomainException;
import com.api.productservice.exceptions.ProductError;
import com.api.productservice.exceptions.ResourceNotFoundException;
import com.api.productservice.inputport.ICategoryService;
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
    public ArrayList<CategoryModel> getCategories() {
        return (ArrayList<CategoryModel>) this.categoryRepository.findAll();
    }

    @Override
    public CategoryModel save(CategoryModel newCategoryModel) throws ProductDomainException {
        List<ProductError> errors = validateDomain(newCategoryModel);
        if (!errors.isEmpty()) {
            throw new ProductDomainException(errors);
        }
        return categoryRepository.save(newCategoryModel);
    }

    @Override
    public CategoryModel findById(Long id)throws ResourceNotFoundException {
        CategoryModel category = this.categoryRepository.findById(id).orElse(null);
        if(category == null){
            throw new ResourceNotFoundException();
        }
        return category;
    }

    @Override
    public CategoryModel findByName(String name) {
        CategoryModel categoryModel=new CategoryModel();
        for(CategoryModel category : this.categoryRepository.findAll()) {
            if(category.getName().equals(name)) {
                return category;
            }
        }
        return null;
    }

    @Override
    public CategoryModel updateById(CategoryModel categoryModel, long id) throws ProductDomainException,ResourceNotFoundException{
        CategoryModel categoria = this.findById(id);
        if(categoria == null){
            throw new ResourceNotFoundException();
        }
        List<ProductError> errors = validateDomain(categoryModel);
        if (!errors.isEmpty()) {
            throw new ProductDomainException(errors);
        }
        categoria.setName(categoryModel.getName());
        categoryRepository.save(categoria);
        return categoria;
    }

    @Override
    public boolean deleteById(Long id)throws ResourceNotFoundException {
        CategoryModel categoryModel=this.findById(id);
        if(categoryModel==null){
            throw new ResourceNotFoundException();
        }
        return true;
    }

    public List<CategoryModel> findByMatchingName(String name){
        return categoryRepository.findByMatchingName(name);
    }
    public List<CategoryModel> findByMatchingId(String id){
        return categoryRepository.findByMatchingId(id);
    }

    private List<ProductError> validateDomain(CategoryModel categoryModel) {
        List<ProductError> errors = new ArrayList<>();

        if (categoryModel.getName() == null || categoryModel.getName().isBlank()) {
            errors.add(new ProductError(EnumErrorCodes.EMPTY_FIELD, "name", "El nombre de la categoria es obligatorio"));
        }

        return errors;

    }
}
