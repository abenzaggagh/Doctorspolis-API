package com.doctorspolis.backend.helper;

import com.doctorspolis.backend.commun.AbstractHelper;
import com.doctorspolis.backend.helper.mapper.DoctorMapper;
import com.doctorspolis.backend.model.DTO.DoctorDTO;
import com.doctorspolis.backend.model.Doctor;
import com.doctorspolis.backend.model.referential.Language;
import com.doctorspolis.backend.model.referential.Speciality;
import com.doctorspolis.backend.repository.referential.LanguageRepository;
import com.doctorspolis.backend.repository.referential.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;

@Component
public class DoctorHelper extends AbstractHelper {

    private final DoctorMapper doctorMapper;

    private final LanguageRepository languageRepository;

    private final SpecialityRepository specialityRepository;

    @Autowired
    public DoctorHelper(DoctorMapper doctorMapper,
                        LanguageRepository languageRepository,
                        SpecialityRepository specialityRepository) {
        this.doctorMapper = doctorMapper;
        this.languageRepository = languageRepository;
        this.specialityRepository = specialityRepository;
    }

    /***
     * This method updates the fields that are included in the DTO.
     * @param doctorDTO DoctorDTO request body
     * @param target Doctor updated entity
     */
    public void updateDoctor(DoctorDTO doctorDTO, Doctor target) {
        super.update(this.setDoctor(doctorDTO), target);
    }

    /**
     *
     * @param doctorDTO
     * @return
     */
    public Doctor setDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = doctorMapper.map(doctorDTO);

        this.setLanguages(doctorDTO, doctor);
        this.setSpecialities(doctorDTO, doctor);

        return doctor;
    }

    public void setSpecialities(DoctorDTO doctorDTO, Doctor doctor) {
        if (!CollectionUtils.isEmpty(doctorDTO.getSpecialities())) {
            ArrayList<Speciality> specialities = new ArrayList<>();

            doctorDTO.getSpecialities().forEach(specialityDTO -> specialityRepository.findSpecialityByCode(specialityDTO.getCode()).ifPresent(specialities::add));
            doctor.setSpecialities(specialities);
        }
    }

    public void setLanguages(DoctorDTO doctorDTO, Doctor doctor) {
        if (!CollectionUtils.isEmpty(doctorDTO.getLanguages())) {
            ArrayList<Language> languages = new ArrayList<>();

            doctorDTO.getLanguages().forEach(languageDTO -> languageRepository.findLanguageByCode(languageDTO.getCode()).ifPresent(languages::add));
            doctor.setLanguages(languages);
        }
    }

    public void prepareSearch(String query) {

    }


}
