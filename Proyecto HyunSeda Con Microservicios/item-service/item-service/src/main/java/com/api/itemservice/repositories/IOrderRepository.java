package com.api.itemservice.repositories;

import com.api.itemservice.models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IOrderRepository extends JpaRepository<OrderModel, Long> {
    @Query("SELECT c FROM OrderModel c WHERE c.clientModel.id = ?1 ")
    Optional<OrderModel> findByClientId(Long id);

    @Query(value = "SELECT MAX(o.id) FROM OrderModel o")
    Long findMaxId();
}
