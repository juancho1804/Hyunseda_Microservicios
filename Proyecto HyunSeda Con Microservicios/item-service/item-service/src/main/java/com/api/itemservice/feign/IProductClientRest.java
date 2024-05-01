package com.api.itemservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "product-service", url = "127.0.0.1:8001")
public interface IProductClientRest {

    @GetMapping("/ProductModel")
    public List<Product> list();

    @GetMapping("/ProductModel/byId/{id}")
    public Product detail(@PathVariable("id") Long id);

    @GetMapping("/ProductModel/byName/{name}")
    public List<Product> findByName(@PathVariable("name") String name);

    @PostMapping ("/ProductModel")
    public Product create(@RequestBody Product productModel);

    @PutMapping("/ProductModel/{id}")
    public Product update(@RequestBody Product productModel, @PathVariable("id") Long id);

    @DeleteMapping("/ProductModel/{id}")
    public void delete(@PathVariable("id") Long id);


}
