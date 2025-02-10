package com.doctorspolis.model.dto;

import com.doctorspolis.model.enumuration.Role;
import com.doctorspolis.utility.AbstractDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
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
public class UserDTO extends AbstractDTO {

    @NotEmpty
    private String email;

    private String phone;

    private Boolean enabled;

    @Enumerated(value = EnumType.STRING)
    private Role role;

}
