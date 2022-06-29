package com.doctorspolis.backend.service;

import com.doctorspolis.backend.commun.AbstractService;
import com.doctorspolis.backend.controller.exception.ResourceNotFoundException;
import com.doctorspolis.backend.model.referential.Country;
import com.doctorspolis.backend.model.referential.DTO.CountryDTO;
import com.doctorspolis.backend.model.referential.DTO.LanguageDTO;
import com.doctorspolis.backend.model.referential.DTO.SpecialityDTO;
import com.doctorspolis.backend.model.referential.Language;
import com.doctorspolis.backend.model.referential.Speciality;
import com.doctorspolis.backend.model.repository.referential.CountryRepository;
import com.doctorspolis.backend.model.repository.referential.LanguageRepository;
import com.doctorspolis.backend.model.repository.referential.SpecialityRepository;
import com.doctorspolis.backend.utility.constants.DoctorspolisConstants;
import com.doctorspolis.backend.utility.mapper.referential.CountryMapper;
import com.doctorspolis.backend.utility.mapper.referential.LanguageMapper;
import com.doctorspolis.backend.utility.mapper.referential.SpecialityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;
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
    public Collection<CountryDTO> getCountries() {
        return countryMapper.toDTOs(this.countryRepository.findAll());
    }

    public CountryDTO getCountryByCode(String code) throws ResourceNotFoundException {
        Optional<Country> country = this.countryRepository.findCountryByCode(code.toLowerCase());
        if (country.isPresent()) {
            return countryMapper.toDTO(country.get());
        } else {
            throw new ResourceNotFoundException("error.referential.country.not.found", code);
        }
    }

    @Cacheable(DoctorspolisConstants.LANGUAGES)
    public Collection<LanguageDTO> getLanguages() {
        return languageMapper.toDTOs(this.languageRepository.findAll());
    }

    public LanguageDTO getLanguageByCode(String code) {
        Optional<Language> language = this.languageRepository.findLanguageByCode(code.toLowerCase());
        if (language.isPresent()) {
            return languageMapper.toDTO(language.get());
        } else {
            throw new ResourceNotFoundException("error.referential.language.not.found", code);
        }
    }

    @Cacheable(DoctorspolisConstants.SPECIALITIES)
    public Collection<SpecialityDTO> getSpecialities() {
        return specialityMapper.toDTOs(this.specialityRepository.findAll());
    }

    public SpecialityDTO getSpecialityByCode(String code) {
        Optional<Speciality> speciality = this.specialityRepository.findSpecialityByCode(code.toLowerCase());
        if (speciality.isPresent()) {
            return specialityMapper.toDTO(speciality.get());
        } else {
            throw new ResourceNotFoundException("error.referential.speciality.not.found", code.toLowerCase());
        }
    }

}
