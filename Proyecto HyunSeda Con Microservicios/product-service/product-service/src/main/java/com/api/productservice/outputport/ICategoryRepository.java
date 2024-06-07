package com.api.productservice.outputport;

import com.api.productservice.models.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepository extends JpaRepository<CategoryModel, Long> {
    @Query("SELECT c FROM CategoryModel c WHERE LOWER(c.name) LIKE %:name%")
    List<CategoryModel> findByMatchingName(@Param("name")String name);


    @Query("SELECT c FROM CategoryModel c WHERE LOWER(CAST(c.categoryId AS string)) LIKE %:id%")
    List<CategoryModel> findByMatchingId(@Param("id") String id);
}
