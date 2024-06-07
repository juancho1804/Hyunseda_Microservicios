package com.api.productservice.inputport;

import com.api.productservice.exceptions.ProductDomainException;
import com.api.productservice.exceptions.ResourceNotFoundException;
import com.api.productservice.models.ProductModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IProductService {
        ArrayList<ProductModel> getProducts();
        ProductModel save(ProductModel newProduct) throws ProductDomainException;
        ProductModel findById(Long id) throws ResourceNotFoundException;
        ProductModel updateById(ProductModel newProduct,long id) throws ProductDomainException,ResourceNotFoundException;
        void deleteById(Long id)throws ResourceNotFoundException;
        Map<String, Integer> contarProductosPorCategoria();
        List<ProductModel> findByMatchingName(String name);
        List<ProductModel> findByMatchingId(String id);
        List<ProductModel> findByMatchingCategoryName(String categoryName);
}
