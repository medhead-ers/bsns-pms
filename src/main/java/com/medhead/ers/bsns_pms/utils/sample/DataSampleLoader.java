package com.medhead.ers.bsns_pms.utils.sample;

import com.medhead.ers.bsns_pms.data.repository.PatientRepository;
import com.medhead.ers.bsns_pms.domain.entity.Patient;
import com.medhead.ers.bsns_pms.domain.valueObject.Gender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSampleLoader {

    @Value("#{new Boolean('${application.loadsampledata}')}")
    private boolean loadSampleData;

    @Bean
    CommandLineRunner initDatabase(PatientRepository patientRepository) {
        if(loadSampleData){
            return args -> {
                patientRepository.save(Patient.builder().firstName("John").lastName("DOE").age(25).gender(Gender.MALE).build());
                patientRepository.save(Patient.builder().firstName("Julia").lastName("SANDERSON").age(48).gender(Gender.FEMALE).build());
                patientRepository.save(Patient.builder().firstName("Duke").lastName("NUKEM").age(62).gender(Gender.UNKNOWN).build());
            };
        }
        return args -> {};
    }
}
