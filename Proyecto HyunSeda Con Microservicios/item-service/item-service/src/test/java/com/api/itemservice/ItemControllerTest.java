package com.api.itemservice;


import com.api.itemservice.controllers.ItemController;
import com.api.itemservice.models.ItemModel;
import com.api.itemservice.models.ProductModel;
import com.api.itemservice.services.IItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ItemControllerTest {

    @Mock
    private IItemService itemService;

    @InjectMocks
    private ItemController itemController;

    /**
     * Set up method that initializes the mock objects.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * @brief Método de prueba para {@link ItemController#getAllItemsFromProducts()}.
     *
     * Este test simula una solicitud GET a la ruta "/items" y verifica que la respuesta tenga el estado HTTP y el cuerpo correctos.
     *
     * @throws Exception si ocurre algún error durante la prueba.
     */
    @Test
    void getAllItemsFromProducts() {
        // Given
        ItemModel item1 = new ItemModel(new ProductModel(), 10);
        ItemModel item2 = new ItemModel(new ProductModel(), 5);
        List<ItemModel> items = Arrays.asList(item1, item2);

        // When
        when(itemService.getItemsFromProducts()).thenReturn(items);

        // Then
        ResponseEntity<List<ItemModel>> response = itemController.getAllItemsFromProducts();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(items, response.getBody());

        verify(itemService, times(1)).getItemsFromProducts();
    }
    /**
     * @brief Método de prueba para {@link ItemController#createItem(ProductModel, int)}.
     *
     * Este test simula la creación de un nuevo item y verifica que el objeto ItemModel devuelto sea el correcto.
     *
     * @throws Exception si ocurre algún error durante la prueba.
     */
    @Test
    void createItem() {
        // Given
        ProductModel productModel = new ProductModel();
        productModel.setId(1L);
        int cantidad = 10;
        ItemModel itemModel = new ItemModel(productModel, cantidad);

        // When
        when(itemService.createItemFromProduct(productModel, cantidad)).thenReturn(itemModel);

        // Then
        ItemModel result = itemController.createItem(productModel, cantidad);
        assertEquals(itemModel, result);

        verify(itemService, times(1)).createItemFromProduct(productModel, cantidad);
    }
}