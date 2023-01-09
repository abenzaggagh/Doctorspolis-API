package com.doctorspolis.backend.model.DTO;

import com.doctorspolis.backend.model.validator.annotation.OpeningHours;
import com.doctorspolis.backend.utility.commun.AbstractDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class WorkScheduleDTO extends AbstractDTO {

    @NotBlank
    private String description;

    @NotEmpty
    @OpeningHours
    @Size(min = 7, max = 7)
    private List<OpeningHoursDTO> openingHours;

}
