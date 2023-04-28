package com.medhead.ers.bsns_pms.application.messaging.message;

import com.medhead.ers.bsns_pms.application.messaging.event.AvailableEvent;
import com.medhead.ers.bsns_pms.domain.entity.Patient;
import org.json.JSONObject;

public class PatientCreatedMessage extends Message{
    public PatientCreatedMessage(Patient patient) {
        this.eventType = AvailableEvent.PatientCreated;
        this.setMetadata(
                new JSONObject().put("emergency", patient).toMap()
        );
    }
}
