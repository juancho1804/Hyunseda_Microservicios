package com.api.itemservice.controllers;

import com.api.itemservice.models.ItemModel;
import com.api.itemservice.models.ProductModel;
import com.api.itemservice.services.IItemService;
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
    public ResponseEntity<List<ItemModel>> getAllItemsFromProducts() {
        // Consumir el servicio de productos para obtener la lista de productos y crear ítems basados en esos productos
        List<ItemModel> items = itemService.getItemsFromProducts();

        // Devolver los ítems creados como respuesta
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
    @PostMapping("/items")
    public ItemModel createItem(@RequestBody ProductModel productModel, @RequestParam int cantidad) {
        ItemModel itemModel=itemService.createItemFromProduct(productModel, cantidad);
        return itemModel;
    }
    @GetMapping("/items/{id}")
    public ProductModel getProductById(@PathVariable("id") Long id) {
        return itemService.findById(id);

    }
}
