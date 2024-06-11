package com.api.itemservice.services;

import com.api.itemservice.models.ItemModel;
import com.api.itemservice.models.ProductModel;

import java.util.List;

public interface IItemService {

    /**
     * @brief Método para obtener una lista de objetos ItemModel a partir de los productos.
     *
     * Este método se utiliza para obtener una lista de objetos ItemModel a partir de los productos existentes.
     *
     * @return Una lista de objetos ItemModel.
     */
    List<ItemModel> getItemsFromProducts();

    /**
     * @brief Método para buscar un producto por su ID.
     *
     * Este método se utiliza para buscar un producto en la base de datos por su ID.
     *
     * @param id El ID del producto.
     * @return El producto si se encuentra, o null en caso contrario.
     */
    ProductModel findById(Long id);

    /**
     * @brief Método para crear un nuevo objeto ItemModel a partir de un producto y una cantidad.
     *
     * Este método se utiliza para crear un nuevo objeto ItemModel a partir de un producto existente y una cantidad especificada.
     *
     * @param product El producto a partir del cual se creará el objeto ItemModel.
     * @param cantidad La cantidad del producto en el objeto ItemModel.
     * @return El objeto ItemModel creado.
     */
    ItemModel createItemFromProduct(ProductModel product, int cantidad);

}
