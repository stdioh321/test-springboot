package com.example.demo.controller;

import com.example.demo.dto.TicketCreateForm;
import com.example.demo.dto.TicketDto;
import com.example.demo.exceptions.ValidationException;
import com.example.demo.service.api.TicketService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<?> deleteById(
            @PathVariable("id") String id
    ) {
        boolean result = ticketService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/tickets/{id}")
    public ResponseEntity<?> patch(
            @PathVariable("id") String id,
            @Valid @RequestBody TicketCreateForm ticketPutForm,
            BindingResult bindingResult
    ) throws JsonProcessingException {
        handleValidationErrors(bindingResult);
        TicketDto ticketDto = ticketService.put(id, ticketPutForm);
        return ResponseEntity.ok(ticketDto);
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<TicketDto>> get() throws JsonProcessingException {
        List<TicketDto> tickets = ticketService.get();
        return ResponseEntity.ok(tickets);
    }

    @PostMapping("/tickets")
    public ResponseEntity<TicketDto> post(
            @Valid @RequestBody TicketCreateForm ticketCreateForm,
            BindingResult bindingResult
    ) throws JsonProcessingException {
        handleValidationErrors(bindingResult);
        TicketDto ticket = ticketService.post(ticketCreateForm);
        ResponseEntity responseEntity = ResponseEntity.status(201).body(ticket);
        return responseEntity;
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