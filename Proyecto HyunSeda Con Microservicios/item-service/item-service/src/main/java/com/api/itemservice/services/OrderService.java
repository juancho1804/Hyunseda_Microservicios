package com.api.itemservice.services;

import com.api.itemservice.models.ClientModel;
import com.api.itemservice.models.OrderModel;
import com.api.itemservice.repositories.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private RestTemplate restTemplate; //

    private final String CLIENT_SERVICE_URL = "http://localhost:8003/clients";


    /**
     * @brief Método para crear una orden para un cliente.
     *
     * Este método busca un cliente por su ID y luego crea una nueva orden para el cliente en la base de datos.
     *
     * @param id El ID del cliente.
     * @param order La orden a crear.
     * @return La orden creada si el cliente existe, o null en caso contrario.
     */
    @Override
    public OrderModel createOrderClient(Long id,OrderModel order) {
        ClientModel clientModel=this.findClientById(id);
        if( clientModel!=null){
            order.setClientModel(clientModel);
            return orderRepository.save(order);
        }
        return null;
    }

    /**
     * @brief Método para obtener todas las órdenes.
     *
     * Este método se utiliza para obtener una lista de todas las órdenes en la base de datos.
     *
     * @return Una lista de todas las órdenes.
     */
    @Override
    public List<OrderModel> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * @brief Método para encontrar el ID más grande entre todas las órdenes.
     *
     * Este método se utiliza para encontrar el ID más grande entre todas las órdenes en la base de datos.
     *
     * @return El ID más grande entre todas las órdenes, o 1 si no hay órdenes.
     */
    @Override
    public Long findMaxId() {
        if(orderRepository.count()==0){
            return 1L;
        }
        return orderRepository.findMaxId();
    }

    /**
     * @brief Método para buscar órdenes por el nombre de usuario del cliente.
     *
     * Este método busca clientes por su nombre de usuario y luego busca órdenes para cada cliente en la base de datos.
     *
     * @param username El nombre de usuario del cliente.
     * @return Una lista de órdenes que pertenecen a los clientes con el nombre de usuario especificado.
     */
    @Override
    public List<OrderModel> findOrdersByUserOfClients(String username) {

        List<ClientModel> clients = this.findClientsByUsername(username);
        List<OrderModel> orders = new ArrayList<>();

        for (ClientModel clientModel : clients) {
            Optional<OrderModel> orderModelOptional = this.findByClientId(clientModel.getId());
            if (orderModelOptional.isPresent()) {
                orders.add(orderModelOptional.get());
            }
        }
        return orders;

    }

    /**
     * @brief Método para buscar un cliente por su ID.
     *
     * Este método realiza una solicitud GET al servicio de clientes para buscar un cliente por su ID.
     *
     * @param id El ID del cliente.
     * @return El cliente si la solicitud fue exitosa, o null en caso contrario.
     */
    public ClientModel findClientById(Long id) {
        ResponseEntity<ClientModel> response = restTemplate.getForEntity(CLIENT_SERVICE_URL + "/byId/" + id, ClientModel.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            ClientModel clientModel = response.getBody();
            return clientModel;
        }
        return null;
    }

    /**
     * @brief Método para buscar clientes por su nombre de usuario.
     *
     * Este método realiza una solicitud GET al servicio de clientes para buscar clientes por su nombre de usuario.
     *
     * @param username El nombre de usuario del cliente.
     * @return Una lista de clientes con el nombre de usuario especificado, o una lista vacía si no se encontraron clientes o hubo un error.
     */
    public List<ClientModel> findClientsByUsername(String username) {
        ResponseEntity<ClientModel[]> response = restTemplate.getForEntity(CLIENT_SERVICE_URL + "/byUsername/" + username, ClientModel[].class);

        if (response.getStatusCode().is2xxSuccessful()) {
            ClientModel[] clientsArray = response.getBody();
            if (clientsArray != null) {
                return Arrays.asList(clientsArray);
            }
        }

        return List.of(); // Devolver una lista vacía si no se encontraron clientes o hubo un error
    }

    /**
     * @brief Método para buscar una orden por el ID del cliente.
     *
     * Este método utiliza el repositorio de órdenes para buscar una orden en la base de datos por el ID del cliente.
     *
     * @param id El ID del cliente.
     * @return Un Optional que puede contener la orden si se encuentra.
     */
    public Optional<OrderModel> findByClientId(Long id) {
        return orderRepository.findByClientId(id);
    }


}
