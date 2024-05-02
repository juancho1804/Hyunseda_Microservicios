package com.api.productservice.services;

import com.api.productservice.exceptions.ProductDomainException;
import com.api.productservice.exceptions.ResourceNotFoundException;
import com.api.productservice.models.ProductModel;

import java.util.ArrayList;
import java.util.Optional;

public interface IProductService {
        public ArrayList<ProductModel> getProducts();
        public ProductModel save(ProductModel newProduct) throws ProductDomainException;
        public ProductModel findById(Long id) throws ResourceNotFoundException;
        public ArrayList<ProductModel> findByName(String name);
        public ProductModel updateById(ProductModel newProduct,long id) throws ProductDomainException,ResourceNotFoundException;
        public boolean deleteById(Long id);
}
