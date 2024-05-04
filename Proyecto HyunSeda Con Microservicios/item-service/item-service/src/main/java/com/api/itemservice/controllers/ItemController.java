package com.api.itemservice.controllers;

import com.api.itemservice.models.ItemModel;
import com.api.itemservice.feign.Product;
import com.api.itemservice.services.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private IItemService itemService;

    @GetMapping
    public List<ItemModel> findAll() {
        return itemService.findAll();
    }

    @GetMapping("/byId/{id}")
    public ItemModel findById(@PathVariable Long id) {
        return itemService.findById(id);
    }
    @GetMapping("/byName/{name}")
    public ArrayList<ItemModel> findByName(@PathVariable("name") String name) {
        return itemService.findByName(name);
    }

    @PostMapping("/createItemFromProduct")
    public ItemModel create(@RequestBody Product product, @RequestParam int cantidad) {
        return itemService.createItemFromProduct(product,cantidad);
    }

    @PutMapping("/{id}")
    public Product update(@RequestBody Product producto, @PathVariable Long id) {
        return itemService.update(producto, id);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        itemService.delete(id);
    }
}
