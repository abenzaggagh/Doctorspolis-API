package com.doctorspolis.backend.repository.referential;

import com.doctorspolis.backend.model.referential.Country;

import org.springframework.data.repository.CrudRepository;

import javax.persistence.Cacheable;
import java.util.Optional;

@Cacheable
public interface CountryRepository extends CrudRepository<Country, Long> {

    Optional<Country> findCountryByCode(String code);

}