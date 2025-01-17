package com.doctorspolis.controller.exception;

import com.doctorspolis.utility.AbstractException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthenticationException extends AbstractException {

    public AuthenticationException(AuthenticationExceptionEnum authenticationExceptionEnum) {
        this.setErrorCode(authenticationExceptionEnum.getErrorCode());
        this.setHttpStatus(authenticationExceptionEnum.getHttpStatus());
        this.setMessage(authenticationExceptionEnum.getMessageCode());
        this.setDetails(authenticationExceptionEnum.getDetailsCode());
    }

}
