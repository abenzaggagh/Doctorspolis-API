package com.doctorspolis.model.enumuration;


import lombok.Getter;

import java.time.DateTimeException;

@Getter
public enum Day {

    MONDAY("Monday", 1),
    TUESDAY("Tuesday", 2),
    WEDNESDAY("Wednesday", 3),
    THURSDAY("Thursday",4),
    FRIDAY("Friday",5),
    SATURDAY("Saturday",6),
    SUNDAY("Sunday", 7);

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

