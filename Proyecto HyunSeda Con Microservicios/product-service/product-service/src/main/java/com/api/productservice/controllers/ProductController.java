package com.api.productservice.controllers;

import com.api.productservice.models.ProductModel;
import com.api.productservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/ProductModel")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping
    public ArrayList<ProductModel> getAllProducts() {
        return this.productService.getProducts();
    }
    @PostMapping
    public ProductModel createProduct(@RequestBody ProductModel product) {
        return this.productService.save(product);
    }
    @GetMapping(path="/byId/{id}")
    public Optional<ProductModel> getProductById(@PathVariable("id") Long id) {
        return this.productService.findById(id);
    }
    @GetMapping(path = "/byName/{name}")
    public ArrayList<ProductModel> getProductByName(@PathVariable("name") String name) {
        return this.productService.findByName(name);
    }
    @PutMapping(path = "/{id}")
    public ProductModel updateById(@RequestBody ProductModel product,@PathVariable("id") long id){
        return this.productService.updateById(product,id);
    }
    @DeleteMapping(path = "{id}")
    public boolean deleteById(@PathVariable("id") long id) {
        boolean ok=this.productService.deleteById(id);
        if(ok){
            System.out.println("Product deleted successfully");
            return true;
        }
        System.out.println("Product not deleted successfully");
        return false;
    }



}
