package com.doctorspolis.model.dto.authentication;

import com.doctorspolis.model.enumuration.Gender;
import com.doctorspolis.model.enumuration.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDTO {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @NotNull
    private Gender gender;

    @Email
    private String email;

    @Size(min = 8)
    private String password;

    @Size(min = 10, max = 10)
    private String phone;

    @NotNull
    private Role role;

}
