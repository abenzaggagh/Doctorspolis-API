package com.doctorspolis.backend.model.repository;

import com.doctorspolis.backend.model.WorkSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WorkScheduleRepository extends JpaRepository<WorkSchedule, Long> { }