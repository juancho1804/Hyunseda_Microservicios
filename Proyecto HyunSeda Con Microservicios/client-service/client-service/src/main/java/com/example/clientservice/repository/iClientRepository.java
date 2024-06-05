package com.example.clientservice.repository;

import com.example.clientservice.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface iClientRepository extends JpaRepository<Client, Integer>{
    @Query("SELECT c FROM Client c WHERE c.username = ?1 AND c.firstName = ?2 AND c.lastName = ?3 AND c.address = ?4")
    Optional<Client> findByUsernameAndFirstNameAndLastNameAndAddress(String username, String firstName, String lastName, String address);

    @Query("SELECT c FROM Client c WHERE c.id = ?1 ")
    Optional<Client> findById(Long id);
    
}
