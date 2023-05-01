package com.medhead.ers.bsns_pms.application.controller;

import com.medhead.ers.bsns_pms.domain.entity.Patient;
import com.medhead.ers.bsns_pms.domain.service.definition.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping("/patients")
    List<Patient> all() {
        return patientService.getAllPatient();
    }

    @GetMapping("/patients/{uuid}")
    Patient one(@PathVariable UUID uuid) {
        return patientService.getPatientById(uuid);
    }

    @PostMapping("/patients")
    @ResponseStatus(HttpStatus.CREATED)
    Patient newPatient(@RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }

}
