package com.doctorspolis.model.dto.authentication;


import com.doctorspolis.model.dto.PatientDTO;
import com.doctorspolis.model.dto.doctor.DoctorDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter

@Builder

@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AuthenticationResponseDTO {

    private PatientDTO patient;

    private DoctorDTO doctor;

    @NotBlank
    private String accessToken;

    @NotBlank
    private String refreshToken;

    private Long expireAt;

    private Long issuedAt;

}
