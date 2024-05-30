package com.api.productservice.inputport;

import com.api.productservice.exceptions.ProductDomainException;
import com.api.productservice.exceptions.ResourceNotFoundException;
import com.api.productservice.models.CategoryModel;
import com.api.productservice.models.ProductModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IProductService {
        public ArrayList<ProductModel> getProducts();
        public ProductModel save(ProductModel newProduct) throws ProductDomainException;
        public ProductModel findById(Long id) throws ResourceNotFoundException;
        public ArrayList<ProductModel> findByName(String name);
        public ProductModel updateById(ProductModel newProduct,long id) throws ProductDomainException,ResourceNotFoundException;
        public void deleteById(Long id)throws ResourceNotFoundException;
        public Map<String, Integer> contarProductosPorCategoria();
        public List<ProductModel> findByMatchingName(String name);
        public List<ProductModel> findByMatchingId(String id);
        List<ProductModel> findByMatchingCategoryName(String categoryName);
}
