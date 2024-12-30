package com.hermes.ticket_service.exception;

public class TicketAlreadyExist extends RuntimeException {

    public TicketAlreadyExist(String message) {
        super(message);
    }
}
