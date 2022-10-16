package com.doctorspolis.backend.model.DTO;

import com.doctorspolis.backend.utility.commun.AbstractDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class UserDTO extends AbstractDTO {

    @NotEmpty
    private String username;

    private Boolean enabled;

    private String role;

    // TODO: Implement a way to retrieve user infos patient and doctor one only or both if they exists.
    private DoctorDTO doctorInfos;

    private PatientDTO patientInfos;

}
