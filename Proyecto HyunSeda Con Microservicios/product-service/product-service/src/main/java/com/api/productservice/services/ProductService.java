package com.api.productservice.services;

import com.api.productservice.models.ProductModel;
import com.api.productservice.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class
ProductService implements IProductService {
    @Autowired
    IProductRepository productRepository;

    public ArrayList<ProductModel>getProducts(){
        return (ArrayList<ProductModel>) productRepository.findAll();
    }

    public ProductModel save(ProductModel newProduct){
        return productRepository.save(newProduct);
    }


    public Optional<ProductModel> findById(Long id){
        return this.productRepository.findById(id);
    }
    public ArrayList<ProductModel> findByName(String name) {
        ArrayList<ProductModel> productList = (ArrayList<ProductModel>) this.productRepository.findAll();
        ArrayList<ProductModel> productsWithName = new ArrayList<>();
        for (ProductModel product : productList) {
            if (name.equalsIgnoreCase(product.getName())) {
                productsWithName.add(product);
            }
        }
        return productsWithName;
    }


    public ProductModel updateById(ProductModel newProduct,long id){
        ProductModel product= productRepository.findById(id).get();
        product.setName(newProduct.getName());
        product.setDescription(newProduct.getDescription());
        product.setPrice(newProduct.getPrice());
        product.setCategory(newProduct.getCategory());
        productRepository.save(product);
        return product;
    }
    public boolean deleteById(Long id){
        try{
            this.productRepository.deleteById(id);
        }catch (Exception e){
            return false;
        }
        return true;
    }

}
