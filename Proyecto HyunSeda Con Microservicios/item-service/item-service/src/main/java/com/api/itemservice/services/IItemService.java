package com.api.itemservice.services;

import com.api.itemservice.models.ItemModel;
import com.api.itemservice.feign.Product;

import java.util.ArrayList;
import java.util.List;

public interface IItemService {
    public List<ItemModel> findAll();

    public ItemModel findById(Long id);

    public ArrayList<ItemModel> findByName(String name);

    public Product create(Product producto);

    public Product update(Product producto, Long id);

    public void delete(Long id);

    ItemModel createItemFromProduct(Product product, int quantity);
}
