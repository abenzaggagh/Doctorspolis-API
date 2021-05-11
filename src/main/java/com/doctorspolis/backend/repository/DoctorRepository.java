package com.doctorspolis.backend.repository;

import com.doctorspolis.backend.model.Doctor;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> { }