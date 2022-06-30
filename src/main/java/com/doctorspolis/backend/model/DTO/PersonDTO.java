package com.doctorspolis.backend.model.DTO;

import com.doctorspolis.backend.utility.commun.AbstractDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public abstract class PersonDTO extends AbstractDTO {

    @NotEmpty
    @Size(min = 3, message = "Firstname should have at least 3 characters.")
    @Size(max = 100, message = "Firstname should have less than 100 characters.")
    private String firstname;

    private String surname;

    @NotEmpty
    @Size(min = 3, message = "Lastname should have at least 3 characters.")
    @Size(max = 100, message = "Lastname should have less than 100 characters.")
    private String lastname;

    // TODO: Change to an enum
    @NotEmpty
    private String gender;

    @NotEmpty
    private String phone;

    @Email
    @NotEmpty
    private String email;

}
