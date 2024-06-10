package com.api.productservice.inputport;

import com.api.productservice.domain.Product;
import com.api.productservice.exceptions.ProductDomainException;
import com.api.productservice.exceptions.ResourceNotFoundException;
import com.api.productservice.models.ProductModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IProductService {
        ArrayList<Product> getProducts();
        Product save(Product newProduct) throws ProductDomainException;
        Product findById(Long id) throws ResourceNotFoundException;
        Product updateById(Product newProduct,long id) throws ProductDomainException,ResourceNotFoundException;
        void deleteById(Long id)throws ResourceNotFoundException;
        Map<String, Integer> contarProductosPorCategoria();
        List<Product> findByMatchingName(String name);
        List<Product> findByMatchingId(String id);
        List<Product> findByMatchingCategoryName(String categoryName);
}
