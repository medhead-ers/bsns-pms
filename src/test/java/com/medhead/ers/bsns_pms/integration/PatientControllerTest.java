package com.medhead.ers.bsns_pms.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medhead.ers.bsns_pms.data.repository.PatientRepository;
import com.medhead.ers.bsns_pms.domain.entity.Patient;
import com.medhead.ers.bsns_pms.domain.valueObject.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
class PatientControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PatientRepository patientRepository;

    @Test
    void test_createPatient() throws Exception {
        // Given
        Patient patient = Patient.builder()
                .firstName("User")
                .lastName("MOCK")
                .age(25)
                .gender(Gender.UNKNOWN)
                .build();

        // When
        mockMvc.perform(post("/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patient)))

                // Then
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(patient.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(patient.getLastName())))
                .andExpect(jsonPath("$.gender", is(patient.getGender().toString())))
                .andExpect(jsonPath("$.age", is(patient.getAge())));

    }

    @Test
    void test_getPatients() throws Exception {
        // Given
        int totalPatientsInRepository = ((int) patientRepository.count());
        // When
        mockMvc.perform(get("/patients"))
                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(totalPatientsInRepository)));
    }

    @Test
    void test_GetOnePatient() throws Exception {
        // Given
        Patient patient = patientRepository.save(Patient.builder()
                .firstName("Test Patient").lastName("MOCK")
                .age(42).gender(Gender.MALE)
                .build());
        UUID patientId = patient.getId();
        // When
        mockMvc.perform(get("/patients/" + patientId))
                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(patient.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(patient.getLastName())))
                .andExpect(jsonPath("$.gender", is(patient.getGender().toString())))
                .andExpect(jsonPath("$.age", is(patient.getAge())));
    }

    @Test
    void test_failGetOnePatient() throws Exception {
        // Given
        UUID patientId = UUID.randomUUID();
        // When
        mockMvc.perform(get("/patients/" + patientId))
                // Then
                .andExpect(status().isNotFound());
    }
}
