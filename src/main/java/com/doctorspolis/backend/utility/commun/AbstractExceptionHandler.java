package com.doctorspolis.backend.utility.commun;

import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.annotation.Resource;
import java.util.Locale;


@ControllerAdvice
public class AbstractExceptionHandler {

    @Resource
    private MessageSource messageSource;

    protected String loadMessage(String code) {
        return messageSource.getMessage(code, null, Locale.ENGLISH);
    }

    protected String loadMessage(String code, Object[] arguments) {
        return messageSource.getMessage(code, arguments, Locale.ENGLISH);
    }
}
