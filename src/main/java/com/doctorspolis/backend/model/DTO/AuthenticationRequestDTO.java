package com.doctorspolis.backend.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.Email;

@Getter
@Setter
@Builder

@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AuthenticationRequestDTO {

    @Email
    private String email;

    private String username;

    private String password;

    private String role;

}
