package com.doctorspolis.backend.exception;

import com.doctorspolis.backend.commun.AbstractException;


public class DoctorNotFoundException extends AbstractException {

    public DoctorNotFoundException(Long doctorID) {
        super("Doctor with ID : {0} is Not Found.", doctorID);
    }
}
