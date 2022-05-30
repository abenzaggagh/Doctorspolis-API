package com.doctorspolis.backend.model.DTO;


import com.doctorspolis.backend.model.referential.DTO.LanguageDTO;
import com.doctorspolis.backend.model.referential.DTO.SpecialityDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class DoctorDTO extends PersonDTO {

    private String overview;

    private String availability;

    private AddressDTO address;

    private List<LanguageDTO> languages;

    private List<SpecialityDTO> specialities;

    private List<WorkScheduleDTO> workSchedule;

}