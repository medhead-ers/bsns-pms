package com.medhead.ers.bsns_pms.domain.service.implementation;

import com.medhead.ers.bsns_pms.application.messaging.exception.MessagePublicationFailException;
import com.medhead.ers.bsns_pms.application.messaging.message.factory.MessageFactory;
import com.medhead.ers.bsns_pms.application.messaging.service.definition.MessagePublisher;
import com.medhead.ers.bsns_pms.data.repository.PatientRepository;
import com.medhead.ers.bsns_pms.domain.entity.Patient;
import com.medhead.ers.bsns_pms.domain.exception.PatientNotFoundException;
import com.medhead.ers.bsns_pms.domain.service.definition.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    MessagePublisher messagePublisher;

    @Override
    public Patient savePatient(Patient patient) {
        Patient patientCreated = patientRepository.save(patient);
        try {
            messagePublisher.publish(MessageFactory.createPatientCreatedMessage(patientCreated));
            return patientCreated;
        }
        // Pour le POC, aucune gestion d'erreur sur la publication de message ne sera implémentée.
        catch (MessagePublicationFailException e){return patientCreated;}
    }

    @Override
    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(UUID uuid) {
        return patientRepository.findById(uuid).orElseThrow(() -> new PatientNotFoundException(uuid));
    }
}
