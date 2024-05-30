package com.api.productservice.inputadapter;

import com.api.productservice.exceptions.ProductDomainException;
import com.api.productservice.exceptions.ResourceNotFoundException;
import com.api.productservice.models.ProductModel;
import com.api.productservice.inputport.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public ProductModel createProduct(@RequestBody ProductModel product) throws ProductDomainException {
        return this.productService.save(product);
    }
    @GetMapping(path="/byId/{id}")
    public ProductModel getProductById(@PathVariable("id") Long id) throws ProductDomainException,ResourceNotFoundException {
        return this.productService.findById(id);
    }
    @GetMapping(path = "/byName/{name}")
    public ArrayList<ProductModel> getProductByName(@PathVariable("name") String name) {
        return this.productService.findByName(name);
    }
    @PutMapping(path = "/{id}")
    public ProductModel updateById(@RequestBody ProductModel product,@PathVariable("id") long id)throws ProductDomainException,ResourceNotFoundException {
        return this.productService.updateById(product,id);
    }
    @DeleteMapping(path = "{id}")
    public void deleteById(@PathVariable("id") long id) throws ResourceNotFoundException{
         productService.deleteById(id);
    }

    @GetMapping(path = "/contarProductosPorCategoria")
    public Map<String, Integer> contarProductosPorCategoria() {
        return this.productService.contarProductosPorCategoria();
    }

    @GetMapping("/byNameMatching/{name}")
    public List<ProductModel> findByMatchingName(@PathVariable String name) {
        // Lógica para buscar productos por coincidencia de cadenas en el nombre
        return productService.findByMatchingName(name);
    }

    @GetMapping("/byIdMatching/{id}")
    public List<ProductModel> findByMatchingId(@PathVariable String id) {
        // Lógica para buscar productos por coincidencia de cadenas en el id
        return productService.findByMatchingId(id);
    }
    @GetMapping("/byCategoryMatching/{name}")
    public List<ProductModel> findByMatchingCategory(@PathVariable String name) {
        return productService.findByMatchingCategoryName(name);
    }



}
