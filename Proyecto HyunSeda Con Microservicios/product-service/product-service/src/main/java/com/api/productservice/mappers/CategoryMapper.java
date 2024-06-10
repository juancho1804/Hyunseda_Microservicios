package com.api.productservice.mappers;

import com.api.productservice.domain.Category;
import com.api.productservice.models.CategoryModel;

public class CategoryMapper {
    /**
     * Convierte de CategoryModel a Category
     *
     * @param categoryModel objeto CategoryModel
     * @return objeto Category
     */
    public static Category toDomain(CategoryModel categoryModel) {
        if (categoryModel == null) return null;

        return new Category(
                categoryModel.getCategoryId(),
                categoryModel.getName()
        );
    }

    /**
     * Convierte de Category a CategoryModel
     *
     * @param category objeto Category
     * @return objeto CategoryModel
     */
    public static CategoryModel toEntity(Category category) {
        if (category == null) return null;

        return new CategoryModel(
                category.getCategoryId(),
                category.getName()
        );
    }
}
