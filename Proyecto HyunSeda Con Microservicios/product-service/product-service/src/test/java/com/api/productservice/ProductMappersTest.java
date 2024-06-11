package com.api.productservice;

import com.api.productservice.domain.Category;
import com.api.productservice.domain.Product;
import com.api.productservice.mappers.ProductMapper;
import com.api.productservice.models.CategoryModel;
import com.api.productservice.models.ProductModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ProductMappersTest {
    /**
     * @brief Método de prueba para {@link ProductMapper#toDomain(ProductModel)}.
     *
     * Este test verifica que la conversión de un objeto ProductModel a un objeto Product se realiza correctamente.
     *
     * @throws Exception si ocurre algún error durante la prueba.
     */
    @Test
    public void testToDomain() {
        CategoryModel categoryModel = new CategoryModel(1L, "Chales");
        ProductModel productModel = new ProductModel(1L, "chal azul", "es azul", 699.99, "image.jpg", categoryModel);
        Product product = ProductMapper.toDomain(productModel);

        assertEquals(1L, product.getId());
        assertEquals("chal azul", product.getName());
        assertEquals("es azul", product.getDescription());
        assertEquals(699.99, product.getPrice());
        assertEquals("image.jpg", product.getImage());
        assertEquals(1L, product.getCategory().getCategoryId());
        assertEquals("Chales", product.getCategory().getName());
    }

    /**
     * @brief Método de prueba para {@link ProductMapper#toDomain(ProductModel)} con un argumento nulo.
     *
     * Este test verifica que la conversión de un objeto ProductModel nulo a un objeto Product devuelve null.
     *
     * @throws Exception si ocurre algún error durante la prueba.
     */
    @Test
    public void testToDomain_Null() {
        Product product = ProductMapper.toDomain(null);
        assertNull(product);
    }

    /**
     * @brief Método de prueba para {@link ProductMapper#toEntity(Product)}.
     *
     * Este test verifica que la conversión de un objeto Product a un objeto ProductModel se realiza correctamente.
     *
     * @throws Exception si ocurre algún error durante la prueba.
     */
    @Test
    public void testToEntity() {
        Category category = new Category(1L, "Chales");
        Product product = new Product(1L, "chal azul", "es azul", 699.99, "image.jpg", category);
        ProductModel productModel = ProductMapper.toEntity(product);

        assertEquals(1L, productModel.getId());
        assertEquals("chal azul", productModel.getName());
        assertEquals("es azul", productModel.getDescription());
        assertEquals(699.99, productModel.getPrice());
        assertEquals("image.jpg", productModel.getImage());
        assertEquals(1L, productModel.getCategory().getCategoryId());
        assertEquals("Chales", productModel.getCategory().getName());
    }

    /**
     * @brief Método de prueba para {@link ProductMapper#toEntity(Product)} con un argumento nulo.
     *
     * Este test verifica que la conversión de un objeto Product nulo a un objeto ProductModel devuelve null.
     *
     * @throws Exception si ocurre algún error durante la prueba.
     */
    @Test
    public void testToEntity_Null() {
        ProductModel productModel = ProductMapper.toEntity(null);
        assertNull(productModel);
    }
}
