package com.example.clientservice.repository;

import com.example.clientservice.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface iClientRepository extends JpaRepository<Client, Integer>{
    @Query("SELECT c FROM Client c WHERE c.id = ?1 ")
    Optional<Client> findById(Long id);

    @Query("SELECT c FROM Client c WHERE c.username = ?1")
    List<Client> findClientsByUsername(String username);
    
}
