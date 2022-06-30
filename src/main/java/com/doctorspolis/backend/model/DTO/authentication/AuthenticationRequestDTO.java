package com.doctorspolis.backend.model.DTO.authentication;

import com.doctorspolis.backend.model.DTO.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder

@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AuthenticationRequestDTO {

    private UserDTO user;

    @NotBlank
    private String username;

    @NotBlank
    private String password;


}
