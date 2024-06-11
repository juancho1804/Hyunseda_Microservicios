package com.example.clientservice;


import com.example.clientservice.model.entity.Client;
import com.example.clientservice.model.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClientService clientService;

    /**
     * @brief Test para el método createClient del ClientController.
     *
     * Este test simula una solicitud POST a la ruta "/clients" y verifica que la respuesta tenga el estado HTTP esperado.
     *
     * @throws Exception si ocurre algún error durante la prueba.
     */
    @Test
    public void testCreateClient() throws Exception {
        Client client = new Client();
        client.setFirstName("Jose");
        client.setEmail("jojo@example.com");

        when(clientService.crearCliente(any(Client.class))).thenReturn(client);

        mockMvc.perform(post("/clients")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isOk());
    }

    /**
     * @brief Test para el método findById del ClientController.
     *
     * Este test simula una solicitud GET a la ruta "/clients/byId/{id}" y verifica que la respuesta tenga el estado HTTP esperado.
     *
     * @throws Exception si ocurre algún error durante la prueba.
     */
    @Test
    public void testFindById() throws Exception {
        Client client = new Client();
        client.setFirstName("Jose");
        client.setEmail("jojo@example.com");

        when(clientService.findById(1L)).thenReturn(Optional.of(client));

        mockMvc.perform(get("/clients/byId/{id}", 1L))
                .andExpect(status().isOk());
    }
    /**
     * @brief Test para el método findClientsByUsername del ClientController.
     *
     * Este test simula una solicitud GET a la ruta "/clients/byUsername/{username}" y verifica que la respuesta tenga el estado HTTP esperado.
     *
     * @throws Exception si ocurre algún error durante la prueba.
     */
    @Test
    public void testFindClientsByUsername() throws Exception {
        Client client = new Client();
        client.setFirstName("Jose");
        client.setEmail("jojo@example.com");

        when(clientService.findClientsByUsername("jojo")).thenReturn(Arrays.asList(client));

        mockMvc.perform(get("/clients/byUsername/{username}", "jojo"))
                .andExpect(status().isOk());
    }
}