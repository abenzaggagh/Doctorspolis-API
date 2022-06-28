package com.doctorspolis.backend.model.repository.referential;

import com.doctorspolis.backend.model.referential.Country;

import javax.persistence.Cacheable;
import java.util.Optional;

@Cacheable
public interface CountryRepository extends ReferentialRepository<Country, Long> {

    Optional<Country> findCountryByCode(String code);

}