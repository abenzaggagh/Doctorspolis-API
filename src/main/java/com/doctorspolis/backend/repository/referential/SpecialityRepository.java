package com.doctorspolis.backend.repository.referential;

import com.doctorspolis.backend.model.referential.Speciality;

import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SpecialityRepository extends ReferentialRepository<Speciality, Long> {

    Optional<Speciality> findSpecialityByCode(String code);

}
