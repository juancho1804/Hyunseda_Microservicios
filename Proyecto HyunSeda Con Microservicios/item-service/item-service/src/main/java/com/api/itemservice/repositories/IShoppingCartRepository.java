package com.api.itemservice.repositories;

import com.api.itemservice.models.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IShoppingCartRepository extends JpaRepository<ItemModel,Long> {
}
