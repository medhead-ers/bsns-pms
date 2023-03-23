package com.medhead.ers.bsns_pms.application.messaging.message.factory;


import com.medhead.ers.bsns_pms.application.messaging.message.PatientCreatedMessage;
import com.medhead.ers.bsns_pms.domain.entity.Patient;

public interface MessageFactory {
    static PatientCreatedMessage createPatientCreatedMessage(Patient patient){
        return new PatientCreatedMessage(patient);
    }
}
