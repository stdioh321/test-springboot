package com.example.demo.service.api;

import com.example.demo.dto.TicketCreateForm;
import com.example.demo.dto.TicketDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TicketService {
    private final String baseUrl = "http://localhost:4000/tickets";
    private final RestTemplate restTemplate;

    public TicketService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<TicketDto> get() throws JsonProcessingException {
        String json = restTemplate.getForObject(baseUrl, String.class);
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(json, new TypeReference<List<TicketDto>>() {
        });
    }

    public TicketDto post(TicketCreateForm ticketCreateForm) throws JsonProcessingException {
        String json = restTemplate.postForObject(baseUrl, ticketCreateForm, String.class);
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(json, TicketDto.class);
    }

    public boolean delete(String id) {
        try {
            restTemplate.delete("%s/%s".formatted(baseUrl, id));
            return true;
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found", ex);
            } else {
                throw ex;
            }
        }
    }

    public String put(String id, TicketCreateForm ticketPutForm) throws JsonProcessingException {
        ResponseEntity response = restTemplate.exchange("%s/%s".formatted(baseUrl,id),HttpMethod.PUT, null,);
        ObjectMapper objectMapper = new ObjectMapper();
        return "asdas";
    }
}