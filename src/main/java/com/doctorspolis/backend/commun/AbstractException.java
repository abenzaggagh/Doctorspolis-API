package com.doctorspolis.backend.commun;


import org.apache.logging.log4j.util.Strings;

public abstract class AbstractException extends RuntimeException {

    private final String message;

    private final String details;

    private Object[] arguments;

    public AbstractException(String message) {
        super(message);
        this.message = message;
        this.details = Strings.EMPTY;
    }

    public AbstractException(String message, Object... arguments) {
        this(message);
        this.arguments = arguments.clone();
    }

    public AbstractException(String message, String details, Object... arguments) {
        super(message);
        this.message = message;
        this.details = details;
        this.arguments = arguments.clone();
    }

    public Object[] getArguments() {
        return arguments;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

}
