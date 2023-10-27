package com.example.demo.controller;

import com.example.demo.dto.TicketCreateForm;
import com.example.demo.dto.TicketDto;
import com.example.demo.exceptions.ValidationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class TicketController {
    @GetMapping("/tickets")
    public ResponseEntity<Map> get() throws JsonProcessingException {
        String json = "{\"a\":1,\"b\":2}";
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.readValue(json, Map.class);

        return ResponseEntity.ok(map);
    }

    @PostMapping("/tickets")
    public ResponseEntity<TicketDto> createTicket(
            @Valid @RequestBody TicketCreateForm ticketCreateForm,
            BindingResult bindingResult
    ) {
        handleValidationErrors(bindingResult);

        TicketDto ticketDto = TicketDto.builder()
                .id(UUID.randomUUID().toString())
                .name(ticketCreateForm.getName())
                .number(ticketCreateForm.getNumber()).build();
        return ResponseEntity.ok(ticketDto);
    }

    private void handleValidationErrors(String message, BindingResult bindingResult) {
        if (bindingResult.getErrorCount() > 0) {
            throw new ValidationException(message, bindingResult.getAllErrors());
        }
    }

    private void handleValidationErrors(BindingResult bindingResult) {
        this.handleValidationErrors("Tudo Errado", bindingResult);
    }
}