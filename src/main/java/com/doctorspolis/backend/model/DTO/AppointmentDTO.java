package com.doctorspolis.backend.model.DTO;

import com.doctorspolis.backend.utility.commun.AbstractDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@ToString

@NoArgsConstructor

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AppointmentDTO extends AbstractDTO {

    @NotBlank
    private Long doctorID;

    @NotBlank
    private Long patientID;

    private String description;

    @NotBlank
    @Size(min = 15, message = "Appointments cannot be less than 15 minutes")
    @Size(max = 60, message = "Appointments cannot be longer than 1 hour")
    private Integer periodTime;

    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy-HH:mm:ss")
    private Date dueDateTime;

}
