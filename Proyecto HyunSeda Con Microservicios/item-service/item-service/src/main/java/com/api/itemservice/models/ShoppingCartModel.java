package com.api.itemservice.models;

import java.util.List;

public class ShoppingCartModel {
    private int id;
    private List<ItemModel> items;

    public ShoppingCartModel() {}
    public ShoppingCartModel(int id, List<ItemModel> items) {
        this.id = id;
        this.items = items;
    }
}
