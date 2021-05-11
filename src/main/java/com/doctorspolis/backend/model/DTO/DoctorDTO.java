package com.doctorspolis.backend.model.DTO;

import com.doctorspolis.backend.model.OpeningHours;
import com.doctorspolis.backend.model.referential.Language;
import com.doctorspolis.backend.model.referential.Speciality;
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
    private List<OpeningHours> openingHours;

    private List<Speciality> Specialities;
    private List<Language> languages;

}