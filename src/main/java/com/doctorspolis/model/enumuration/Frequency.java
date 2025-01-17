package com.doctorspolis.model.enumuration;

import lombok.Getter;

@Getter
public enum Frequency {

    AS_NEEDED("AS_NEEDED"),

    BEFORE_MEALS("BEFORE_MEALS"),
    AFTER_MEALS("AFTER_MEALS"),

    ONCE_A_DAY("ONCE_A_DAY"),
    TWICE_A_DAY("TWICE_A_DAY"),
    THREE_TIMES_A_DAY("THREE_TIMES_A_DAY"),
    FOUR_TIMES_A_DAY("FOUR_TIMES_A_DAY"),
    FIVE_TIMES_A_DAY("FIVE_TIMES_A_DAY"),

    EVERY_HOUR("EVERY_HOUR"),
    EVERY_2_HOURS("EVERY_2_HOURS"),
    EVERY_4_HOURS("EVERY_4_HOURS"),
    EVERY_6_HOURS("EVERY_6_HOURS"),

    BEFORE_BED("BEFORE_BED");

    private final String label;

    Frequency(String label) {
        this.label = label;
    }

}
