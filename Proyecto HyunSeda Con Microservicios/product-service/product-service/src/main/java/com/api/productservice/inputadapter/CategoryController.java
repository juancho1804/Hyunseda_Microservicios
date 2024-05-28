package com.api.productservice.inputadapter;

import com.api.productservice.exceptions.ProductDomainException;
import com.api.productservice.exceptions.ResourceNotFoundException;
import com.api.productservice.models.CategoryModel;
import com.api.productservice.inputport.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/CategoryModel")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ArrayList<CategoryModel> getCategories() {
        return categoryService.getCategories();
    }
    @PostMapping
    public CategoryModel save(@RequestBody CategoryModel newCategoryModel)throws ProductDomainException {
        return categoryService.save(newCategoryModel);
    }

    @GetMapping(path = "/byId/{id}")
    public CategoryModel findById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return categoryService.findById(id);
    }

    @GetMapping(path = "/byName/{name}")
    public  CategoryModel findByName(@PathVariable("name") String name) {
        return categoryService.findByName(name);
    }

   @PutMapping(path = "{id}")
    public CategoryModel updateById(@RequestBody CategoryModel categoryModel, @PathVariable("id") long id) throws ProductDomainException,ResourceNotFoundException{
        return categoryService.updateById(categoryModel,id);
    }

    @DeleteMapping(path = "{id}")
    public boolean deleteById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        if(categoryService.deleteById(id)) {
            System.out.println("Categoria borrada satisfactoriamente");
            return true;
        }
        System.out.println("Error al eliminar la categoria");
        return false;
    }
}
