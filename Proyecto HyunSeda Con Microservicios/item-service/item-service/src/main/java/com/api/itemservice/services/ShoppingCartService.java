package com.api.itemservice.services;

import com.api.itemservice.controllers.ItemController;
import com.api.itemservice.models.ItemModel;
import com.api.itemservice.models.ProductModel;
import com.api.itemservice.models.ShoppingCartModel;
import com.api.itemservice.repositories.IShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {

    ShoppingCartModel shoppingCartModel;
    @Autowired
    IShoppingCartRepository shoppingCartRepository;
    @Autowired
    IItemService itemService;



    public ShoppingCartModel createShoppingCart(ShoppingCartModel shoppingCartModel) {
        return shoppingCartRepository.save(shoppingCartModel);
    }
    public void agregarItem(ItemModel itemModel) {
        this.shoppingCartModel.getItems().add(itemModel);
    }

}
