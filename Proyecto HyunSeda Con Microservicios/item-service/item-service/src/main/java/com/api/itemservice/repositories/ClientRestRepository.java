package com.api.itemservice.repositories;

import com.api.itemservice.models.ClientModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClientRestRepository {
    @Autowired
    private RestTemplate restClient;

    private final String CLIENT_SERVICE_URL = "http://localhost:8003/clients";


}
