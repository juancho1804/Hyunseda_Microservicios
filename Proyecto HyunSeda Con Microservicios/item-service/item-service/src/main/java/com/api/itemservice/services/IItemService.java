package com.api.itemservice.services;

import com.api.itemservice.models.ItemModel;
import com.api.itemservice.models.ProductModel;

import java.util.List;

public interface IItemService {
    List<ItemModel> getItemsFromProducts();
    ProductModel findById(Long id);
    ItemModel createItemFromProduct(ProductModel product, int cantidad);

}
