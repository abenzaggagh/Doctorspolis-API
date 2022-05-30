package com.doctorspolis.backend.model.DTO;

import com.doctorspolis.backend.model.enumeration.Day;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class OpeningHoursDTO {

    // TODO: Change day to Enum format
    // TODO: Validate if no day is repeated
    @NotNull
    private Day day;

    @NotNull
    private Boolean opens;

    @JsonFormat(pattern="HH:mm:ss")
    private Date openingTime;

    @JsonFormat(pattern="HH:mm:ss")
    private Date closingTime;

}
