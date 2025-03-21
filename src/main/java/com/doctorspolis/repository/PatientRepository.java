package com.doctorspolis.repository;

import com.doctorspolis.model.data.Patient;
import com.doctorspolis.model.data.authentication.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByUser(User user);

}