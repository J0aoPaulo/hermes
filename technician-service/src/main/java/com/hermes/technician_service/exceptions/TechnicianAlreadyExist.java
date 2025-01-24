package com.hermes.technician_service.exceptions;

public class TechnicianAlreadyExist extends RuntimeException {

    public TechnicianAlreadyExist(String msg) {
        super(msg);
    }
}
