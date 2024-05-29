package com.api.productservice.outputport;

import com.api.productservice.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductRepository extends JpaRepository<ProductModel,Long> {
    @Query("SELECT p FROM ProductModel p WHERE LOWER(p.name) LIKE %:name%")
    List<ProductModel>findByMatchingName(@Param("name")String name);
}
