package com.hermes.ticket_service.controller;

import com.hermes.ticket_service.controller.dto.CreateTicketDto;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/tickets")
public class TicketController {

    @PostMapping
    @Transactional
    public ResponseEntity<Void> createTicket(CreateTicketDto createTicketDto) {
        return null;
    }
}
