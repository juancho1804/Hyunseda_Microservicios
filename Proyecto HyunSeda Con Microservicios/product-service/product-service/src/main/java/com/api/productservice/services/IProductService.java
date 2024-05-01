package com.api.productservice.services;

import com.api.productservice.models.ProductModel;

import java.util.ArrayList;
import java.util.Optional;

public interface IProductService {
        public ArrayList<ProductModel> getProducts();
        public ProductModel save(ProductModel newProduct);
        public Optional<ProductModel> findById(Long id);
        public ArrayList<ProductModel> findByName(String name);
        public ProductModel updateById(ProductModel newProduct,long id);
        public boolean deleteById(Long id);
}
