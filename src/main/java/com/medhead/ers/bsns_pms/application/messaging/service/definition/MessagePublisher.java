package com.medhead.ers.bsns_pms.application.messaging.service.definition;

import com.medhead.ers.bsns_pms.application.messaging.exception.MessagePublicationFailException;
import com.medhead.ers.bsns_pms.application.messaging.message.Message;

public interface MessagePublisher {
    public void publish(Message message) throws MessagePublicationFailException;
}
