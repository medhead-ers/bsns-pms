package com.medhead.ers.bsns_pms.application.messaging.exception;

public class CannotProcessJobException extends Exception{
    public CannotProcessJobException(Exception exception) {
        super("MessageListener cannot process job properly. Cause :" + exception.getMessage());
    }
}
