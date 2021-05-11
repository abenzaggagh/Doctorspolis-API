package com.doctorspolis.backend.model.DTO;

import com.doctorspolis.backend.commun.AbstractDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public abstract class PersonDTO extends AbstractDTO {

    private String firstname;
    private String surname;
    private String lastname;

    private String gender;

    private String phone;
    private String email;

}
