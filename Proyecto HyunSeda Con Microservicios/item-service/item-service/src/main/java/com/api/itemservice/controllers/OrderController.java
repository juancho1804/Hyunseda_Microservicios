package com.api.itemservice.controllers;

import com.api.itemservice.models.OrderModel;
import com.api.itemservice.services.IOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping(path = "/{id}")
    @Operation(summary = "Crear una orden para un cliente",
            description = "Crea una nueva orden para un cliente especificado por su ID.")
    @ApiResponse(responseCode = "200", description = "Orden creada con éxito",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderModel.class),
                    examples = @ExampleObject(value = "{\"orderId\": 1, \"clientId\": 1, \"orderDetails\": \"Detalles de la orden\"}")))
    @ApiResponse(responseCode = "422", description = "Datos de la orden inválidos",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{\"errors\": [{\"code\": \"INVALID_DATA\", \"message\": \"Los datos de la orden son inválidos\"}]}")))
    public OrderModel createOrderClient(@PathVariable("id") Long id, @RequestBody OrderModel order){
        return this.orderService.createOrderClient(id, order);
    }

    @GetMapping
    @Operation(summary = "Obtener todas las órdenes",
            description = "Obtiene una lista de todas las órdenes almacenadas en la base de datos.")
    @ApiResponse(responseCode = "200", description = "Lista de órdenes recuperada exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderModel.class),
                    examples = @ExampleObject(value = "[{\"orderId\": 1, \"clientId\": 1, \"orderDetails\": \"Detalles de la orden\"}, {\"orderId\": 2, \"clientId\": 2, \"orderDetails\": \"Detalles de la orden\"}]")))
    public List<OrderModel> getAllOrders() {
        return  orderService.getAllOrders();
    }

    @GetMapping("/findByUser/{username}")
    @Operation(summary = "Buscar órdenes por nombre de usuario",
            description = "Obtiene una lista de órdenes realizadas por un usuario específico.")
    @ApiResponse(responseCode = "200", description = "Lista de órdenes recuperada exitosamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OrderModel.class),
                    examples = @ExampleObject(value = "[{\"orderId\": 1, \"clientId\": 1, \"username\": \"jojo\", \"orderDetails\": \"Detalles de la orden\"}]")))
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "{\"errors\": [{\"code\": \"NOT_FOUND\", \"message\": \"Usuario no encontrado\"}]}")))
    public List<OrderModel>findOrdersByUserOfClients(@PathVariable("username") String username){
        return this.orderService.findOrdersByUserOfClients(username);
    }

    @GetMapping("/maxId")
    @Operation(summary = "Obtener el ID máximo de las órdenes",
            description = "Obtiene el valor del ID más alto de las órdenes en la base de datos.")
    @ApiResponse(responseCode = "200", description = "ID máximo recuperado exitosamente",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = "100")))
    public Long findMaxId(){
        return this.orderService.findMaxId();
    }
}
