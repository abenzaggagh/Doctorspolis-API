package com.doctorspolis.repository;

import com.doctorspolis.model.data.doctor.Speciality;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialityRepository extends CrudRepository<Speciality, Integer> {

    List<Speciality> findAll();

    Speciality findByCode(@NotNull String code);

}
