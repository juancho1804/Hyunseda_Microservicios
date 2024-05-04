package com.api.itemservice.feign;

import com.api.itemservice.models.ItemModel;
import com.api.itemservice.services.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("serviceFeign")
public class ProductFeign implements IItemService {
    @Autowired
    private IProductClientRest clientFeign;

    public ItemModel createItemFromProduct(Product product, int cantidad){
        //product = clientFeign.create(product);
        ItemModel itemModel = new ItemModel(product,cantidad);
        return itemModel;
    }
    public List<ItemModel> findAll() {
        return clientFeign.list().stream().map(p -> new ItemModel(p)).collect(Collectors.toList());
    }

    public ItemModel findById(Long id) {
        return new ItemModel(clientFeign.findProductbyId(id),1);
    }


    public ArrayList<ItemModel> findByName(String name) {
        return (ArrayList<ItemModel>) clientFeign.findByName(name).stream().map(p -> new ItemModel(p, 1)).collect(Collectors.toList());
    }


    public Product create(Product product) {
        return clientFeign.create(product);
    }


    public Product update(Product product, Long id) {
        return clientFeign.update(product, id);
    }


    public void delete(Long id) {
        clientFeign.delete(id);
    }
}
