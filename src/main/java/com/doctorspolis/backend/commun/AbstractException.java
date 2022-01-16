package com.doctorspolis.backend.commun;

import java.text.MessageFormat;

public abstract class AbstractException extends RuntimeException {

    public AbstractException(String message) {
        super(message);
    }

    public AbstractException(String messageFormat, Object... args) {
        super(MessageFormat.format(messageFormat, args));
    }

}
