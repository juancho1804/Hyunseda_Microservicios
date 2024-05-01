package com.api.productservice.controllers;

import com.api.productservice.models.CategoryModel;
import com.api.productservice.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

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
    public CategoryModel save(@RequestBody CategoryModel newCategoryModel) {
        return categoryService.save(newCategoryModel);
    }

    @GetMapping(path = "/byId/{id}")
    public Optional<CategoryModel> findById(@PathVariable("id") Long id) {
        return categoryService.findById(id);
    }

    @GetMapping(path = "/byName/{name}")
    public ArrayList<CategoryModel> findByName(@PathVariable("name") String name) {
        return categoryService.findByName(name);
    }

   @PutMapping(path = "{id}")
    public CategoryModel updateById(@RequestBody CategoryModel categoryModel, @PathVariable("id") long id) {
        return categoryService.updateById(categoryModel,id);
    }

    @DeleteMapping(path = "{id}")
    public boolean deleteById(@PathVariable("id") Long id) {
        if(categoryService.deleteById(id)) {
            System.out.println("Categoria borrada satisfactoriamente");
            return true;
        }
        System.out.println("Error al eliminar la categoria");
        return false;
    }
}
