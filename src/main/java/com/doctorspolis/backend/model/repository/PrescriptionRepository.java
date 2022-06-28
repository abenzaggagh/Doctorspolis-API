package com.doctorspolis.backend.model.repository;

import com.doctorspolis.backend.model.Patient;
import com.doctorspolis.backend.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    List<Prescription> findAllByPatient(Patient patient);

}