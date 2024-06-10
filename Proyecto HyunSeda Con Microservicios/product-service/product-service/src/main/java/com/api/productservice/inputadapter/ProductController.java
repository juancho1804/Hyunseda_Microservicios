package com.api.productservice.inputadapter;

import com.api.productservice.domain.Product;
import com.api.productservice.exceptions.ProductDomainException;
import com.api.productservice.exceptions.ResourceNotFoundException;
import com.api.productservice.inputport.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Obtiene todos los productos", description = "Obtiene una lista de todos los productos.")
    @ApiResponse(responseCode = "200", description = "Lista de productos recuperada exitosamente")
    @GetMapping
    public ArrayList<Product> getAllProducts() {
        return this.productService.getProducts();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) throws ProductDomainException {
        return this.productService.save(product);
    }

    @PutMapping(path = "/{id}")
    public Product updateById(@RequestBody Product product,@PathVariable("id") long id)throws ProductDomainException,ResourceNotFoundException {
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
    public List<Product> findByMatchingName(@PathVariable String name) {
        // Lógica para buscar productos por coincidencia de cadenas en el nombre
        return productService.findByMatchingName(name);
    }

    @GetMapping("/byIdMatching/{id}")
    public List<Product> findByMatchingId(@PathVariable String id) {
        // Lógica para buscar productos por coincidencia de cadenas en el id
        return productService.findByMatchingId(id);
    }
    @GetMapping("/byCategoryMatching/{name}")
    public List<Product> findByMatchingCategory(@PathVariable String name) {
        return productService.findByMatchingCategoryName(name);
    }



}
