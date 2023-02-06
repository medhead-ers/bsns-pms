package com.medhead.ers.bsns_pms.data.repository;

import com.medhead.ers.bsns_pms.domain.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PatientRepository  extends JpaRepository<Patient, UUID> {

}
