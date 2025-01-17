package com.doctorspolis.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthenticationExceptionEnum {

    UNKNOWN_EXCEPTION("E000",
            HttpStatus.INTERNAL_SERVER_ERROR,
            "auth.internal_error.message",
            "auth.internal_error.message.detail"),
    USER_NOT_FOUND("E001",
            HttpStatus.NOT_FOUND,
            "auth.user_not_found.message",
            "auth.user_not_found.message.detail"),
    BAD_CREDENTIALS("E002",
            HttpStatus.UNAUTHORIZED,
            "auth.bad_credentials.message",
            "auth.bad_credentials.message.detail"),
    USER_PHONE_ALREADY_EXISTS("E003",
            HttpStatus.BAD_REQUEST,
            "auth.phone_already_exists.message",
            "auth.phone_already_exists.message.detail"),
    USER_EMAIL_ALREADY_EXISTS("E004",
            HttpStatus.BAD_REQUEST,
            "auth.email_already_exists.message",
            "auth.email_already_exists.message.detail"),
    INCORRECT_PHONE_FORMAT("E005",
            HttpStatus.BAD_REQUEST,
            "auth.incorrect_phone_format.message",
            "auth.incorrect_phone_format.message.detail"),
    INCORRECT_OTP_CODE("E006",
            HttpStatus.UNAUTHORIZED,
            "auth.incorrect_otp_code.message",
            "auth.incorrect_otp_code.message.detail"),
    OTP_CODE_NOT_FOUND("E007",
             HttpStatus.UNAUTHORIZED,
            "auth.otp_code_not_found.message",
                     "auth.otp_code_not_found.message.detail"),
    SAME_PASSWORD_CODE("E008",
            HttpStatus.NOT_ACCEPTABLE,
            "auth.same_password_code.message",
            "auth.same_password_code.message.detail"),
    INVALID_PASSWORDS("E009",
            HttpStatus.BAD_REQUEST,
            "auth.same_password_code.message",
            "auth.same_password_code.message.detail"),
    INCORRECT_EMAIL("E010",
            HttpStatus.BAD_REQUEST,
            "auth.incorrect_email.message",
            "auth.incorrect_email.message.detail");


    private final String errorCode;

    private final HttpStatus httpStatus;

    private final String messageCode;

    private final String detailsCode;

}
