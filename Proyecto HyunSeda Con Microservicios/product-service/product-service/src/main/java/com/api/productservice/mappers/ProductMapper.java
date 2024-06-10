package com.api.productservice.mappers;

import com.api.productservice.domain.Category;
import com.api.productservice.domain.Product;
import com.api.productservice.models.CategoryModel;
import com.api.productservice.models.ProductModel;

public class ProductMapper {
    /**
     * Convierte de ProductModel a Product
     *
     * @param productModel objeto ProductModel
     * @return objeto Product
     */
    public static Product toDomain(ProductModel productModel) {
        if (productModel == null) return null;

        Category category = CategoryMapper.toDomain(productModel.getCategory());

        return new Product(
                productModel.getId(),
                productModel.getName(),
                productModel.getDescription(),
                productModel.getPrice(),
                productModel.getImage(),
                category
        );
    }

    /**
     * Convierte de Product a ProductModel
     *
     * @param product objeto Product
     * @return objeto ProductModel
     */
    public static ProductModel toEntity(Product product) {
        if (product == null) return null;

        CategoryModel categoryModel = CategoryMapper.toEntity(product.getCategory());

        return new ProductModel(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getImage(),
                categoryModel
        );
    }
}
