package com.api.productservice.services;

import com.api.productservice.domain.Product;
import com.api.productservice.exceptions.EnumErrorCodes;
import com.api.productservice.exceptions.ProductDomainException;
import com.api.productservice.exceptions.ProductError;
import com.api.productservice.exceptions.ResourceNotFoundException;
import com.api.productservice.inputport.IProductService;
import com.api.productservice.mappers.CategoryMapper;
import com.api.productservice.mappers.ProductMapper;
import com.api.productservice.models.CategoryModel;
import com.api.productservice.models.ProductModel;
import com.api.productservice.outputport.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService implements IProductService {
    @Autowired
    IProductRepository productRepository;

    public ArrayList<Product> getProducts() {
        List<ProductModel> productModels = productRepository.findAll();
        ArrayList<Product> products = new ArrayList<>();
        for (ProductModel productModel : productModels) {
            products.add(ProductMapper.toDomain(productModel));
        }
        return products;
    }

    public Product save(Product newProduct) throws ProductDomainException {
        List<ProductError> errors = validateDomain(newProduct);
        if (!errors.isEmpty()) {
            throw new ProductDomainException(errors);
        }
        ProductModel productModel = ProductMapper.toEntity(newProduct);
        return ProductMapper.toDomain(productRepository.save(productModel));
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


    public Product findById(Long id) throws ResourceNotFoundException {
        ProductModel prod = this.productRepository.findById(id).orElse(null);
        if (prod == null) {
            throw new ResourceNotFoundException();
        }
        return ProductMapper.toDomain(prod);
    }

    public ArrayList<Product> findByName(String name) {
        ArrayList<ProductModel> productList = (ArrayList<ProductModel>) this.productRepository.findAll();
        ArrayList<Product> productsWithName = new ArrayList<>();
        for (ProductModel product : productList) {
            if (name.equalsIgnoreCase(product.getName())) {
                productsWithName.add(ProductMapper.toDomain(product));
            }
        }
        return productsWithName;
    }


    public Product updateById(Product newProduct, long id) throws ProductDomainException, ResourceNotFoundException {
        ProductModel product = this.productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new ResourceNotFoundException();
        }
        List<ProductError> errors = validateDomain(newProduct);
        if (!errors.isEmpty()) {
            throw new ProductDomainException(errors);
        }
        product.setName(newProduct.getName());
        product.setDescription(newProduct.getDescription());
        product.setPrice(newProduct.getPrice());
        product.setImage(newProduct.getImage());
        CategoryModel categoryModel = CategoryMapper.toEntity(newProduct.getCategory());
        product.setCategory(categoryModel);

        productRepository.save(product);
        return ProductMapper.toDomain(product);
    }


    public void deleteById(Long id) throws ResourceNotFoundException{

        if(productRepository.findById(id).isPresent()){
            this.productRepository.deleteById(id);
        }else{
            throw new ResourceNotFoundException();
        }

    }
    public List<Product> findByMatchingName(String name) {
        List<ProductModel> productModels = this.productRepository.findByMatchingName(name.toLowerCase());
        List<Product> products = new ArrayList<>();
        for (ProductModel productModel : productModels) {
            products.add(ProductMapper.toDomain(productModel));
        }
        return products;
    }
    public List<Product> findByMatchingId(String id) {
        List<ProductModel> productModels = this.productRepository.findByMatchingId(id);
        List<Product> products = new ArrayList<>();
        for (ProductModel productModel : productModels) {
            products.add(ProductMapper.toDomain(productModel));
        }
        return products;
    }
    public List<Product> findByMatchingCategoryName(String categoryName) {
        List<ProductModel> productModels = this.productRepository.findByMatchingCategoryName(categoryName);
        List<Product> products = new ArrayList<>();
        for (ProductModel productModel : productModels) {
            products.add(ProductMapper.toDomain(productModel));
        }
        return products;
    }



    private List<ProductError> validateDomain(Product product) {
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
