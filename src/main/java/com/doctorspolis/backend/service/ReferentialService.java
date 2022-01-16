package com.doctorspolis.backend.service;

import com.doctorspolis.backend.commun.AbstractService;
import com.doctorspolis.backend.helper.mapper.CountryMapper;
import com.doctorspolis.backend.model.referential.Country;
import com.doctorspolis.backend.model.referential.DTO.CountryDTO;
import com.doctorspolis.backend.repository.referential.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@CacheConfig
public class ReferentialService extends AbstractService {

    private final CountryMapper countryMapper;

    private final CountryRepository countryRepository;

    @Autowired
    public ReferentialService(CountryMapper countryMapper, CountryRepository countryRepository) {
        this.countryMapper = countryMapper;
        this.countryRepository = countryRepository;
    }

    @Cacheable("Countries")
    public List<CountryDTO> getCountries() {
        return countryMapper.map((List<Country>) this.countryRepository.findAll());
    }

}
