package com.doctorspolis.model.dto.authentication;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class EmailVerificationDTO {

    @Email
    private String email;

}
