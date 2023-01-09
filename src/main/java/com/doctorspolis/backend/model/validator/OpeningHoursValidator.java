package com.doctorspolis.backend.model;

import com.doctorspolis.backend.model.DTO.WorkScheduleDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OpeningHoursValidator implements ConstraintValidator<WorkScheduleDTO, String> {

    @Override
    public void initialize(WorkScheduleDTO constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }

}
