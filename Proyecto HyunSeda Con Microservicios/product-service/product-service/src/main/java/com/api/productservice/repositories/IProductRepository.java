package com.api.productservice.repositories;

import com.api.productservice.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<ProductModel,Long> {
}
