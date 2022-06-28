package com.doctorspolis.backend.repository;

import com.doctorspolis.backend.model.Appointment;
import com.doctorspolis.backend.model.Doctor;
import com.doctorspolis.backend.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAllByDoctorAndDueDateTime(Doctor doctor, LocalDateTime date);

    List<Appointment> findAllByDoctor(Doctor doctor);

    List<Appointment> findAllByPatient(Patient patient);

}