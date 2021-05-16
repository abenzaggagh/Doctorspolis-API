package com.doctorspolis.backend.model.DTO;

import com.doctorspolis.backend.model.OpeningHours;
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
public class WorkScheduleDTO {

    private String description;

    private List<OpeningHoursDTO> openingHours;
}
