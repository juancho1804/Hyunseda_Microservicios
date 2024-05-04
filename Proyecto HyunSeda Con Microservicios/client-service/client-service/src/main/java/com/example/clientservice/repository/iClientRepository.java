package com.example.clientservice.repository;

import com.example.clientservice.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iClientRepository extends JpaRepository<Client, Integer>{
    
}
