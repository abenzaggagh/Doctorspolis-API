package com.doctorspolis.repository;

import com.doctorspolis.model.data.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}