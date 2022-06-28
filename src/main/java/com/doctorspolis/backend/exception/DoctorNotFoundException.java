package com.doctorspolis.backend.exception;


public class DoctorNotFoundException extends ResourceNotFoundException {

    // TODO: Move the message to the message Ancestor
    public DoctorNotFoundException(Long doctorID) {
        // TODO: Create a custom ErrorDTO when exception DTO.
        super("Doctor with ID : {0} is Not Found.", String.valueOf(doctorID));
    }

}
