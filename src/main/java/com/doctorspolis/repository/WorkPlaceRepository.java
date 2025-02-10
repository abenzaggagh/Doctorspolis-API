package com.doctorspolis.repository;

import com.doctorspolis.model.data.doctor.WorkPlace;
import com.doctorspolis.model.data.doctor.WorkSchedule;
import org.springframework.data.repository.CrudRepository;

public interface WorkPlaceRepository extends CrudRepository<WorkPlace, Long> { }