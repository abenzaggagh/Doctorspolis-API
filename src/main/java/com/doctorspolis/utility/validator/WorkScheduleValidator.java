package com.doctorspolis.utility.validator;

import com.doctorspolis.model.dto.doctor.OpeningHoursDTO;
import com.doctorspolis.model.dto.doctor.WorkScheduleDTO;
import com.doctorspolis.model.enumuration.Day;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.*;

public class WorkScheduleValidator implements ConstraintValidator<WorkScheduleValid, WorkScheduleDTO> {

    @Override
    public boolean isValid(WorkScheduleDTO workScheduleDTO,
                           ConstraintValidatorContext constraintValidatorContext) {
        if (workScheduleDTO == null ||
            workScheduleDTO.getOpeningHours() == null ||
            workScheduleDTO.getOpeningHours().isEmpty()) {
            return false;
        }

        if (workScheduleDTO.getOpeningHours().size() == 7) {
            List<String> openingHoursDays = workScheduleDTO.getOpeningHours().stream().map(OpeningHoursDTO::getDay).toList();
            List<String> days = Arrays.stream(Day.values()).map(Day::getLabel).toList();

            Set<String> openingHoursSet = new HashSet<>(openingHoursDays);
            Set<String> daysSet = new HashSet<>(days);

            if (openingHoursSet.equals(daysSet)) {
                for (OpeningHoursDTO openingHoursDTO : workScheduleDTO.getOpeningHours()) {
                    if (openingHoursDTO.getOpens()) {
                        if (openingHoursDTO.getHasBreak()) {
                            if (openingHoursDTO.getOpeningTime().isAfter(openingHoursDTO.getBreakTime())   ||
                                openingHoursDTO.getOpeningTime().isAfter(openingHoursDTO.getResumeTime())  ||
                                openingHoursDTO.getOpeningTime().isAfter(openingHoursDTO.getClosingTime()) ||
                                openingHoursDTO.getBreakTime().isAfter(openingHoursDTO.getResumeTime())    ||
                                openingHoursDTO.getBreakTime().isAfter(openingHoursDTO.getClosingTime())   ||
                                openingHoursDTO.getResumeTime().isAfter(openingHoursDTO.getClosingTime())) {
                                return false;
                            }
                        } else {
                            if (openingHoursDTO.getOpeningTime().isAfter(openingHoursDTO.getClosingTime())) {
                                return false;
                            }
                        }
                    }
                }

                return true;
            }

            return false;
        }

        return false;
    }

}
