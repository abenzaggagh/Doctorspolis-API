package com.doctorspolis.backend.service;

import com.doctorspolis.backend.commun.AbstractService;
import com.doctorspolis.backend.exception.ResourceNotFoundException;
import com.doctorspolis.backend.helper.mapper.referential.CountryMapper;
import com.doctorspolis.backend.helper.mapper.referential.LanguageMapper;
import com.doctorspolis.backend.helper.mapper.referential.SpecialityMapper;
import com.doctorspolis.backend.model.referential.Country;
import com.doctorspolis.backend.model.referential.DTO.CountryDTO;
import com.doctorspolis.backend.model.referential.DTO.LanguageDTO;
import com.doctorspolis.backend.model.referential.DTO.SpecialityDTO;
import com.doctorspolis.backend.model.referential.Language;
import com.doctorspolis.backend.model.referential.Speciality;
import com.doctorspolis.backend.repository.referential.CountryRepository;
import com.doctorspolis.backend.repository.referential.LanguageRepository;
import com.doctorspolis.backend.repository.referential.SpecialityRepository;
import com.doctorspolis.backend.utility.constants.DoctorspolisConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@CacheConfig
public class ReferentialService extends AbstractService {

    private final CountryMapper countryMapper;

    private final LanguageMapper languageMapper;

    private final SpecialityMapper specialityMapper;

    private final CountryRepository countryRepository;

    private final LanguageRepository languageRepository;

    private final SpecialityRepository specialityRepository;

    @Autowired
    public ReferentialService(CountryMapper countryMapper, CountryRepository countryRepository,
                              LanguageMapper languageMapper, LanguageRepository languageRepository,
                              SpecialityMapper specialityMapper, SpecialityRepository specialityRepository) {
        this.countryMapper = countryMapper;
        this.countryRepository = countryRepository;
        this.languageMapper = languageMapper;
        this.languageRepository = languageRepository;
        this.specialityMapper = specialityMapper;
        this.specialityRepository = specialityRepository;
    }

    @Cacheable(DoctorspolisConstants.COUNTRIES)
    public List<CountryDTO> getCountries() {
        return countryMapper.map((List<Country>) this.countryRepository.findAll());
    }

    public CountryDTO getCountryByCode(String code) throws ResourceNotFoundException {
        Optional<Country> country = this.countryRepository.findCountryByCode(code.toLowerCase());
        if (country.isPresent()) {
            return countryMapper.map(country.get());
        } else {
            throw new ResourceNotFoundException("Country with Code : {0} is Not Found.", code.toLowerCase());
        }
    }

    @Cacheable(DoctorspolisConstants.LANGUAGES)
    public List<LanguageDTO> getLanguages() {
        return languageMapper.map((List<Language>) this.languageRepository.findAll());
    }

    public LanguageDTO getLanguageByCode(String code) {
        Optional<Language> language = this.languageRepository.findLanguageByCode(code.toLowerCase());
        if (language.isPresent()) {
            return languageMapper.map(language.get());
        } else {
            throw new ResourceNotFoundException("Language with Code : {0} is Not Found.", code.toLowerCase());
        }
    }

    @Cacheable(DoctorspolisConstants.SPECIALITIES)
    public List<SpecialityDTO> getSpecialities() {
        return specialityMapper.map((List<Speciality>) this.specialityRepository.findAll());
    }

    public SpecialityDTO getSpecialityByCode(String code) {
        Optional<Speciality> speciality = this.specialityRepository.findSpecialityByCode(code.toLowerCase());
        if (speciality.isPresent()) {
            return specialityMapper.map(speciality.get());
            // return new SpecialityDTO();
        } else {
            throw new ResourceNotFoundException("Language with Code : {0} is Not Found.", code.toLowerCase());
        }
    }

}
