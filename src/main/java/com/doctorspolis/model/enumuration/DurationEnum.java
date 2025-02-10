package com.doctorspolis.model.enumuration;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum DurationEnum {

    FIFTEEN_MINUTES(15),
    THIRTY_MINUTES(30),
    ONE_HOUR(60),
    UNDEFINED(-1);

    private final int duration;

    DurationEnum(int duration) {
        this.duration = duration;
    }

    public static DurationEnum getDuration(Integer value) {
        return Arrays.stream(DurationEnum.values())
                .filter(durationEnumEnum -> durationEnumEnum.duration == value)
                .findFirst()
                .orElse(null);
    }

}
