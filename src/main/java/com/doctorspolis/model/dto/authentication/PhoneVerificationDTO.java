package com.doctorspolis.model.dto.authentication;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PhoneVerificationDTO {

    @Size(min = 10, max= 10)
    private String phone;

}
