package com.api.productservice.services;

import com.api.productservice.exceptions.EnumErrorCodes;
import com.api.productservice.exceptions.ProductDomainException;
import com.api.productservice.exceptions.ProductError;
import com.api.productservice.exceptions.ResourceNotFoundException;
import com.api.productservice.models.ProductModel;
import com.api.productservice.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

    public ProductModel save(ProductModel newProduct)throws ProductDomainException {
        List<ProductError> errors = validateDomain(newProduct);
        if (!errors.isEmpty()) {
            throw new ProductDomainException(errors);
        }
        return productRepository.save(newProduct);
    }


    public ProductModel findById(Long id) throws ResourceNotFoundException{
        ProductModel prod = this.productRepository.findById(id).orElse(null);
        if(prod == null){
            throw new ResourceNotFoundException();
        }

        return prod;

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


    public ProductModel updateById(ProductModel newProduct,long id)throws ProductDomainException, ResourceNotFoundException{
        ProductModel product= this.findById(id);
        if(product == null){
            throw new ResourceNotFoundException();
        }
        List<ProductError> errors = validateDomain(product);

        if (!errors.isEmpty()) {
            throw new ProductDomainException(errors);
        }
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


    private List<ProductError> validateDomain(ProductModel product) {
        List<ProductError> errors = new ArrayList<>();

        if (product.getName() == null || product.getName().isBlank()) {
            errors.add(new ProductError(EnumErrorCodes.EMPTY_FIELD, "name", "El nombre del producto es obligatorio"));
        }

        if (product.getPrice() <= 0) {
            errors.add(new ProductError(EnumErrorCodes.INVALID_NUMBER, "price",
                    "El precio del producto es obligatorio y mayor a cero"));
        }
        return errors;

    }

}