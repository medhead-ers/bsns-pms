package com.medhead.ers.bsns_pms.units;

import com.medhead.ers.bsns_pms.domain.entity.Patient;
import com.medhead.ers.bsns_pms.domain.exception.InvalidPatientAgeException;
import com.medhead.ers.bsns_pms.domain.valueObject.Gender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.util.Assert;

class PatientEntityTest {
    @Test
    void test_patientEntityStructure() throws InvalidPatientAgeException {
        // Given
        String patientFirstName = "demo";
        String patientLastName = "user";
        int patientAge = 32;
        String patientGender = "MALE";
        // When
        Patient patient = new Patient();
        patient.setFirstName("demo".toLowerCase());
        patient.setLastName("user".toLowerCase());
        patient.setAge(patientAge);
        patient.setGender(Gender.valueOf(patientGender));
        // Then
        Assert.isTrue(patient.getFirstName().equals(patientFirstName.substring(0, 1).toUpperCase() + patientFirstName.substring(1)),
                "Patient first name should start with a capital letter.");
        Assert.isTrue(patient.getLastName().equals(patientLastName.toUpperCase()),
                "Patient last name should be fully capitalized.");
        Assert.isTrue(patient.getAge().equals(patientAge),
                "Patient age should not be updated and under 150 y.o.");
        Assert.isTrue(patient.getGender().equals(Gender.MALE),
                "Patient gender should not be updated");
    }

    @ParameterizedTest
    @ValueSource(ints = {(Patient.MINIMUM_PATIENT_AGE-1),(Patient.MAXIMUM_PATIENT_AGE+1)})
    void test_patientThrowsExceptionWhenAgeIsTooOld(int badPatientAge) {
        // Given
        Patient patient = new Patient();
        // Then
        Assertions.assertThrows(InvalidPatientAgeException.class, () -> {
        // When
            patient.setAge(badPatientAge);
        });
    }

    @Test
    void test_patientDefaultGenderIsEqualsToUnknown() {
        // Given
        Patient patient = new Patient();
        // When
            // Nothing is given
        // Then
        Assert.isTrue(patient.getGender().equals(Gender.UNKNOWN),
                "Default patient gender should be \"UNKNOWN\" when nothing is given.");
    }
}
