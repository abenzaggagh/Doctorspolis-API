package com.doctorspolis.repository;

import com.doctorspolis.model.data.Patient;
import com.doctorspolis.model.data.User;
import com.doctorspolis.model.data.doctor.WorkSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WorkScheduleRepository extends CrudRepository<WorkSchedule, Long> { }