package com.doctorspolis.model.dto.doctor;

import com.doctorspolis.model.dto.PersonDTO;
import com.doctorspolis.utility.validator.WorkScheduleValid;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class DoctorDTO extends PersonDTO {

    private String overview;

    private String availability;

    private WorkPlaceDTO workPlace;

    private SpecialityDTO speciality;

    private List<LanguageDTO> languages;

    private List<EducationDTO> educations;

    @WorkScheduleValid
    private WorkScheduleDTO workSchedule;

    private List<String> appointmentTypes;

    private List<AppointmentReasonDTO> appointmentReasons;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorDTO doctorDTO = (DoctorDTO) o;
        return Objects.equals(super.getID(), doctorDTO.getID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }

}
