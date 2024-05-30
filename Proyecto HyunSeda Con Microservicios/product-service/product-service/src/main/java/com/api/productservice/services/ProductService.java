package com.api.productservice.services;

import com.api.productservice.exceptions.EnumErrorCodes;
import com.api.productservice.exceptions.ProductDomainException;
import com.api.productservice.exceptions.ProductError;
import com.api.productservice.exceptions.ResourceNotFoundException;
import com.api.productservice.inputport.IProductService;
import com.api.productservice.models.CategoryModel;
import com.api.productservice.models.ProductModel;
import com.api.productservice.outputport.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService implements IProductService {
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

    public Map<String, Integer> contarProductosPorCategoria() {
        List<ProductModel> productList = productRepository.findAll();
        Map<String, Integer> productosPorCategoria = new HashMap<>();

        for (ProductModel product : productList) {
            String categoria = product.getCategory().getName();
            if (productosPorCategoria.containsKey(categoria)) {
                // Si la categoría ya existe en el mapa, incrementa el contador
                productosPorCategoria.put(categoria, productosPorCategoria.get(categoria) + 1);
            } else {
                // Si la categoría no existe en el mapa, añade la categoría con un contador inicial de 1
                productosPorCategoria.put(categoria, 1);
            }
        }

        return productosPorCategoria;
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
        product.setImage(newProduct.getImage());
        product.setCategory(newProduct.getCategory());

        productRepository.save(product);
        return product;
    }
    public void deleteById(Long id) throws ResourceNotFoundException{

        if(productRepository.findById(id).isPresent()){
            this.productRepository.deleteById(id);
        }else{
            throw new ResourceNotFoundException();
        }

    }
    public List<ProductModel>findByMatchingName(String name){
        return this.productRepository.findByMatchingName(name.toLowerCase());
    }
    public List<ProductModel>findByMatchingId(String id){
        return this.productRepository.findByMatchingId(id);
    }
    public List<ProductModel> findByMatchingCategoryName(String categoryName){
        return this.productRepository.findByMatchingCategoryName(categoryName);
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
