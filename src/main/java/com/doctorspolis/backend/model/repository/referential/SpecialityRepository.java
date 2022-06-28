package com.doctorspolis.backend.model.repository.referential;

import com.doctorspolis.backend.model.referential.Speciality;

import java.util.Optional;

public interface SpecialityRepository extends ReferentialRepository<Speciality, Long> {

    Optional<Speciality> findSpecialityByCode(String code);

}
