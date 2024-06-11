package com.api.itemservice.controllers;

import com.api.itemservice.models.ItemModel;
import com.api.itemservice.models.ProductModel;
import com.api.itemservice.services.IItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private IItemService itemService; // Suponiendo que tienes un servicio llamado ItemService para manejar la lógica de negocio

    @GetMapping("/items")
    @Operation(summary = "Obtener todos los ítems de productos",
            description = "Obtiene una lista de todos los ítems creados a partir de los productos.")
    @ApiResponse(responseCode = "200", description = "Lista de ítems recuperada exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ItemModel.class),
                    examples = @ExampleObject(value = "[{\"itemId\": 1, \"productId\": 1, \"cantidad\": 10}, {\"itemId\": 2, \"productId\": 2, \"cantidad\": 5}]")))
    public ResponseEntity<List<ItemModel>> getAllItemsFromProducts() {
        // Consumir el servicio de productos para obtener la lista de productos y crear ítems basados en esos productos
        List<ItemModel> items = itemService.getItemsFromProducts();

        // Devolver los ítems creados como respuesta
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
    @PostMapping("/items")
    @Operation(summary = "Crear un ítem",
            description = "Crea un nuevo ítem a partir de un producto especificado y una cantidad.")
    @ApiResponse(responseCode = "200", description = "Ítem creado con éxito",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ItemModel.class),
                    examples = @ExampleObject(value = "{\"itemId\": 1, \"productId\": 1, \"cantidad\": 10}")))
    @ApiResponse(responseCode = "422", description = "Datos de ítem inválidos",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{\"errors\": [{\"code\": \"INVALID_DATA\", \"message\": \"Los datos del ítem son inválidos\"}]}")))
    public ItemModel createItem(@RequestBody ProductModel productModel, @RequestParam int cantidad) {
        return itemService.createItemFromProduct(productModel, cantidad);
    }
}
