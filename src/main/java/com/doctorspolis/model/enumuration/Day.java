package com.doctorspolis.model.enumuration;


import lombok.Getter;

import java.time.DateTimeException;

@Getter
public enum Day {

    MONDAY("MONDAY", 1),
    TUESDAY("TUESDAY", 2),
    WEDNESDAY("WEDNESDAY", 3),
    THURSDAY("THURSDAY",4),
    FRIDAY("FRIDAY",5),
    SATURDAY("SATURDAY",6),
    SUNDAY("SUNDAY", 7);

    public static final int COUNT = 7;

    private final String label;

    private final Integer value;

    private static final Day[] ENUMS = Day.values();

    Day(String label, Integer value) {
        this.label = label;
        this.value = value;
    }

    public static Day of(int day) {
        if (day < 1 || day > 7) {
            throw new DateTimeException("Invalid value for Day: " + day);
        }
        return ENUMS[day - 1];
    }

}

