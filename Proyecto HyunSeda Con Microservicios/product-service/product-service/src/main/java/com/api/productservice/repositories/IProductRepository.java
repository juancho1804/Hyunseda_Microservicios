package com.api.productservice.repositories;

import com.api.productservice.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IProductRepository extends JpaRepository<ProductModel,Long> {
}
