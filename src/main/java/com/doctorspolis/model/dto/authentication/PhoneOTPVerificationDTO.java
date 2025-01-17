package com.doctorspolis.model.dto.authentication;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@NoArgsConstructor
public class PhoneOTPVerificationDTO {

    private String otpCode;

    private String phone;

}