package com.medhead.ers.bsns_pms.domain.service.implementation;

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
    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
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
