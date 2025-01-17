package com.doctorspolis.repository;

import com.doctorspolis.model.data.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
