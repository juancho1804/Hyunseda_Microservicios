package com.api.itemservice.repositories;

import com.api.itemservice.models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IOrderRepository extends JpaRepository<OrderModel, Long> {

    /**
     * @brief Método para buscar una orden por el ID del cliente.
     *
     * Este método utiliza una consulta personalizada para buscar una orden en la base de datos por el ID del cliente.
     *
     * @param id El ID del cliente.
     * @return Un Optional que puede contener la orden si se encuentra.
     */
    @Query("SELECT c FROM OrderModel c WHERE c.clientModel.id = ?1 ")
    Optional<OrderModel> findByClientId(Long id);

    /**
     * @brief Método para encontrar el ID más grande entre todas las órdenes.
     *
     * Este método utiliza una consulta personalizada para encontrar el ID más grande entre todas las órdenes en la base de datos.
     *
     * @return El ID más grande entre todas las órdenes.
     */
    @Query(value = "SELECT MAX(o.id) FROM OrderModel o")
    Long findMaxId();
}
