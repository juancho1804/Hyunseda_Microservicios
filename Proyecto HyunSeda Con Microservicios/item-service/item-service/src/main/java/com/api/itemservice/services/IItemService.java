package com.api.itemservice.services;

import com.api.itemservice.models.ItemModel;
import com.api.itemservice.models.ProductModel;

import java.util.List;

public interface IItemService {
    public List<ItemModel> getItemsFromProducts();
    public ProductModel findById(Long id);
    public ItemModel createItemFromProduct(ProductModel product, int cantidad);

}
