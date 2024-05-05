package com.api.itemservice.repositories;

import com.api.itemservice.models.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IItemRepository extends JpaRepository<ItemModel, Long> {
}
