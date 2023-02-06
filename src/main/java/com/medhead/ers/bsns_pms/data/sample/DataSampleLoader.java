package com.medhead.ers.bsns_pms.data.sample;

import com.medhead.ers.bsns_pms.data.repository.PatientRepository;
import com.medhead.ers.bsns_pms.domain.entity.Patient;
import com.medhead.ers.bsns_pms.domain.valueObject.Gender;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSampleLoader {
    @Bean
    CommandLineRunner initDatabase(PatientRepository patientRepository) {
        return args -> {
            patientRepository.save(Patient.builder().firstName("John").lastName("Doe").age(25).gender(Gender.MALE).build());
            patientRepository.save(Patient.builder().firstName("Julia").lastName("Sanderson").age(48).gender(Gender.FEMALE).build());
            patientRepository.save(Patient.builder().firstName("Duke").lastName("Nukem").age(62).gender(Gender.UNKNOWN).build());
        };
    }
}
