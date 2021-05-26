package com.doctorspolis.backend.helper;

import com.doctorspolis.backend.model.DTO.DoctorDTO;
import com.doctorspolis.backend.model.Doctor;
import com.doctorspolis.backend.model.referential.DTO.LanguageDTO;
import com.doctorspolis.backend.model.referential.DTO.SpecialityDTO;
import com.doctorspolis.backend.model.referential.Language;
import com.doctorspolis.backend.model.referential.Speciality;
import com.doctorspolis.backend.repository.referential.LanguageRepository;
import com.doctorspolis.backend.repository.referential.SpecialityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Component
public class DoctorHelper {

    private final LanguageRepository languageRepository;

    private final SpecialityRepository specialityRepository;

    @Autowired
    public DoctorHelper(LanguageRepository languageRepository,
                        SpecialityRepository specialityRepository) {
        this.languageRepository = languageRepository;
        this.specialityRepository = specialityRepository;
    }

    public void updateDoctor(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] propertyDescriptors;
        propertyDescriptors = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            Object srcValue = src.getPropertyValue(propertyDescriptor.getName());
            if (srcValue == null) emptyNames.add(propertyDescriptor.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public void setSpecialities(DoctorDTO doctorDTO, Doctor doctor) {
        ArrayList<Speciality> specialities = new ArrayList<>();

        if (!CollectionUtils.isEmpty(doctorDTO.getSpecialities())) {
            for(SpecialityDTO specialityDTO: doctorDTO.getSpecialities()) {
                specialities.add(specialityRepository.findSpecialityByCode(specialityDTO.getCode()));
            }
            doctor.setSpecialities(specialities);
        }
    }

    public void setLanguages(DoctorDTO doctorDTO, Doctor doctor) {
        ArrayList<Language> languages = new ArrayList<>();

        if (!CollectionUtils.isEmpty(doctorDTO.getLanguages())) {
            for(LanguageDTO languageDTO: doctorDTO.getLanguages()) {
                languages.add(languageRepository.findLanguageByCode(languageDTO.getCode()));
            }
            doctor.setLanguages(languages);
        }
    }


}
