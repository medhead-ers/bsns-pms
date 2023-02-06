package com.medhead.ers.bsns_pms.application.messaging.exception;

public class MessagePublicationFailException extends Exception {
    public MessagePublicationFailException(Exception exception) {
        super(exception.getMessage());
    }
}
