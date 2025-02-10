package com.doctorspolis.model.dto.doctor;

import com.doctorspolis.model.data.doctor.OpeningHours;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class WorkScheduleDTO {

    private Boolean active;

    private String description;

    private Set<OpeningHoursDTO> openingHours;

}
