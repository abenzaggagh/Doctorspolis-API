package com.doctorspolis.backend.repository;

import com.doctorspolis.backend.model.Doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor> findAllByFirstnameOrLastname(String firstname, String lastname, Pageable pageable);

}