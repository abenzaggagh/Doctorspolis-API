package com.doctorspolis.backend.repository.referential;

import com.doctorspolis.backend.commun.ReferentialRepository;

import com.doctorspolis.backend.model.referential.Speciality;

import org.springframework.data.jpa.repository.Query;

public interface SpecialityRepository extends ReferentialRepository<Speciality, Long> {

    @Query(value = "SELECT speciality FROM Speciality speciality WHERE speciality.code = ?1")
    Speciality findSpecialityByCode(String code);

}
