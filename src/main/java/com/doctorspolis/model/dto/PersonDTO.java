package com.doctorspolis.model.dto;

import com.doctorspolis.model.enumuration.Gender;
import com.doctorspolis.utility.AbstractDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.ALWAYS)
public abstract class PersonDTO extends AbstractDTO {

    private String profilePicture;

    @NotEmpty
    @Size(min = 3, message = "Firstname should have at least 3 characters.")
    @Size(max = 100, message = "Firstname should have less than 100 characters.")
    private String firstname;

    private String surname;

    @NotEmpty
    @Size(min = 3, message = "Lastname should have at least 3 characters.")
    @Size(max = 100, message = "Lastname should have less than 100 characters.")
    private String lastname;

    @NotEmpty
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @NotEmpty
    private String phone;

    @Email
    @NotEmpty
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

}
