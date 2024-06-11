package com.api.productservice;

import com.api.productservice.domain.Category;
import com.api.productservice.mappers.CategoryMapper;
import com.api.productservice.models.CategoryModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CategoryMappersTest {

    /**
     * @brief Método de prueba para {@link CategoryMapper#toDomain(CategoryModel)}.
     *
     * Este test verifica que la conversión de un objeto CategoryModel a un objeto Category se realiza correctamente.
     *
     * @throws Exception si ocurre algún error durante la prueba.
     */
    @Test
    public void testToDomain() {
        CategoryModel categoryModel = new CategoryModel(1L, "Chales");
        Category category = CategoryMapper.toDomain(categoryModel);

        assertEquals(1L, category.getCategoryId());
        assertEquals("Chales", category.getName());
    }

    /**
     * @brief Método de prueba para {@link CategoryMapper#toDomain(CategoryModel)} con un argumento nulo.
     *
     * Este test verifica que la conversión de un objeto CategoryModel nulo a un objeto Category devuelve null.
     *
     * @throws Exception si ocurre algún error durante la prueba.
     */
    @Test
    public void testToDomain_Null() {
        Category category = CategoryMapper.toDomain(null);
        assertNull(category);
    }

    /**
     * @brief Método de prueba para {@link CategoryMapper#toEntity(Category)}.
     *
     * Este test verifica que la conversión de un objeto Category a un objeto CategoryModel se realiza correctamente.
     *
     * @throws Exception si ocurre algún error durante la prueba.
     */
    @Test
    public void testToEntity() {
        Category category = new Category(1L, "Chales");
        CategoryModel categoryModel = CategoryMapper.toEntity(category);

        assertEquals(1L, categoryModel.getCategoryId());
        assertEquals("Chales", categoryModel.getName());
    }

    /**
     * @brief Método de prueba para {@link CategoryMapper#toEntity(Category)} con un argumento nulo.
     *
     * Este test verifica que la conversión de un objeto Category nulo a un objeto CategoryModel devuelve null.
     *
     * @throws Exception si ocurre algún error durante la prueba.
     */
    @Test
    public void testToEntity_Null() {
        CategoryModel categoryModel = CategoryMapper.toEntity(null);
        assertNull(categoryModel);
    }
}
