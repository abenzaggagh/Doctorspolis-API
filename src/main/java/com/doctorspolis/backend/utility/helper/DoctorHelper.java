package com.doctorspolis.backend.utility.helper;

import com.doctorspolis.backend.model.DTO.DoctorDTO;
import com.doctorspolis.backend.model.Doctor;
import com.doctorspolis.backend.model.WorkSchedule;
import com.doctorspolis.backend.model.referential.Language;
import com.doctorspolis.backend.model.referential.Speciality;
import com.doctorspolis.backend.model.repository.referential.LanguageRepository;
import com.doctorspolis.backend.model.repository.referential.SpecialityRepository;
import com.doctorspolis.backend.utility.commun.AbstractHelper;
import com.doctorspolis.backend.utility.mapper.DoctorMapper;
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
        super.replace(this.setDoctor(doctorDTO), target);
    }

    public void replaceDoctor(DoctorDTO doctorDTO, Doctor target) {
        super.replace(this.setDoctor(doctorDTO), target);
    }

    /**
     *
     * @param doctorDTO
     * @return
     */
    public Doctor setDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = doctorMapper.toEntity(doctorDTO);

        this.setLanguages(doctorDTO, doctor);
        this.setSpecialities(doctorDTO, doctor);

        return doctor;
    }

    public void clear(Doctor doctor) {
        doctor.getWorkSchedule().clear();
        doctor.getLanguages().clear();
        doctor.getSpecialities().clear();

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


    public WorkSchedule updateWorkSchedule(WorkSchedule workSchedule, WorkSchedule target) {
        if (workSchedule.getOpeningHours() == null) {
            super.update(workSchedule, target);
        } else {
            // target.getOpeningHours().clear();
            target.setDescription(workSchedule.getDescription());
            target.setOpeningHours(workSchedule.getOpeningHours());
        }

        return target;
    }

    public void prepareSearch(String query) {

    }


}
