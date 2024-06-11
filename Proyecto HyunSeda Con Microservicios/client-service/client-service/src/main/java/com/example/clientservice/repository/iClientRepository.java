package com.example.clientservice.repository;

import com.example.clientservice.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface iClientRepository extends JpaRepository<Client, Integer>{
    /**
     * @brief Método para buscar un cliente por su ID.
     *
     * Este método utiliza una consulta JPQL para buscar un cliente en la base de datos por su ID.
     *
     * @param id El ID del cliente.
     * @return Un Optional que puede contener el cliente si se encuentra.
     */
    @Query("SELECT c FROM Client c WHERE c.id = ?1 ")
    Optional<Client> findById(Long id);

    /**
     * @brief Método para buscar clientes por su nombre de usuario.
     *
     * Este método utiliza una consulta JPQL para buscar clientes en la base de datos por su nombre de usuario.
     *
     * @param username El nombre de usuario del cliente.
     * @return Una lista de clientes con el nombre de usuario especificado.
     */
    @Query("SELECT c FROM Client c WHERE c.username = ?1")
    List<Client> findClientsByUsername(String username);
    
}
