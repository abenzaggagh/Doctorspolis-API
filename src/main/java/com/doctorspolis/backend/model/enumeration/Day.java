package com.doctorspolis.backend.model.enumeration;

import lombok.Getter;

import java.time.DateTimeException;
import java.time.DayOfWeek;

@Getter
public enum Day {

    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    public static final int COUNT = 7;

    private final String label;

    private static final Day[] ENUMS = Day.values();

    Day(String label) {
        this.label = label;
    }

    public static Day of(int day) {
        if (day < 1 || day > 7) {
            throw new DateTimeException("Invalid value for Day: " + day);
        }
        return ENUMS[day - 1];
    }

}

