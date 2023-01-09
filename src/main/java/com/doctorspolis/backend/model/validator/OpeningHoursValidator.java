package com.doctorspolis.backend.model.validator;

import com.doctorspolis.backend.model.DTO.OpeningHoursDTO;
import com.doctorspolis.backend.model.enumeration.Day;
import com.doctorspolis.backend.model.validator.annotation.OpeningHours;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OpeningHoursValidator implements ConstraintValidator<OpeningHours, List<OpeningHoursDTO>> {

    @Override
    public void initialize(OpeningHours constraintAnnotation) {}

    @Override
    public boolean isValid(List<OpeningHoursDTO> openingHoursList, ConstraintValidatorContext context) {
        HashSet<Day> days = new HashSet<>(List.of(Day.values()));
        if (!ObjectUtils.isEmpty(openingHoursList) && openingHoursList.size() == Day.COUNT) {
            Set<Day> openingHoursDays = new HashSet<>();
            for (OpeningHoursDTO openingHoursDTO: openingHoursList) {
                openingHoursDays.add(openingHoursDTO.getDay());
            }
            return openingHoursDays.containsAll(days);
        }
        return false;
    }


}
