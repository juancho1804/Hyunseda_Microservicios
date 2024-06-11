package com.api.itemservice.services;

import com.api.itemservice.models.ItemModel;
import com.api.itemservice.models.ProductModel;
import com.api.itemservice.repositories.IItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ItemService implements IItemService {
    @Autowired
    private IItemRepository itemRepository;

    @Autowired
    private RestTemplate restTemplate;

    // URL del servicio de productos
    private final String PRODUCT_SERVICE_URL = "http://localhost:8001/ProductModel";

    /**
     * @brief Método para obtener una lista de objetos ItemModel a partir de los productos.
     *
     * Este método realiza una solicitud GET al servicio de productos para obtener la lista de productos y luego mapea los productos a ítems.
     *
     * @return Una lista de objetos ItemModel si la solicitud fue exitosa, o una lista vacía en caso contrario.
     */
    @Override
    public List<ItemModel> getItemsFromProducts() {
        // Realizar una solicitud GET al servicio de productos para obtener la lista de productos
        ResponseEntity<ProductModel[]> response = restTemplate.getForEntity(PRODUCT_SERVICE_URL, ProductModel[].class);

        // Verificar si la solicitud fue exitosa
        if (response.getStatusCode() == HttpStatus.OK) {
            // Mapear los productos a ítems
            ProductModel[] products = response.getBody();
            List<ItemModel> items = new ArrayList<>();
            for (ProductModel product : products) {
                // Lógica para crear ítems desde productos
                ItemModel item = new ItemModel(product,0);
                items.add(item);
            }
            return items;
        } else {
            // Manejar errores de solicitud
            // Por ejemplo, puedes lanzar una excepción o devolver una lista vacía
            return Collections.emptyList();
        }
    }

    /**
     * @brief Método para buscar un producto por su ID.
     *
     * Este método realiza una solicitud GET al servicio de productos para buscar un producto por su ID.
     *
     * @param id El ID del producto.
     * @return El producto si la solicitud fue exitosa, o null en caso contrario.
     */
    @Override
    public ProductModel findById(Long id) {
        ResponseEntity<ProductModel> response = restTemplate.getForEntity(PRODUCT_SERVICE_URL + "/byId/" + id, ProductModel.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            ProductModel product = response.getBody();
            return product;
        }
        return null;
    }

    /**
     * @brief Método para crear un nuevo objeto ItemModel a partir de un producto y una cantidad.
     *
     * Este método busca un producto por su ID y luego crea un nuevo objeto ItemModel a partir del producto y la cantidad especificada.
     *
     * @param product El producto a partir del cual se creará el objeto ItemModel.
     * @param cantidad La cantidad del producto en el objeto ItemModel.
     * @return El objeto ItemModel creado.
     */
    @Override
    public ItemModel createItemFromProduct(ProductModel product, int cantidad) {
        product=this.findById(product.getId());
        ItemModel item = new ItemModel(product,cantidad);
        itemRepository.save(item);
        System.out.println(item.getProduct().getId());
        return item;
    }
}
