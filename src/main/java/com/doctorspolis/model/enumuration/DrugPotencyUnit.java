package com.doctorspolis.model.enumuration;

import lombok.Getter;

@Getter
public enum DrugPotencyUnit {

    MG("mg"),
    MCG("mg"),
    G("g"),
    ML("ml"),
    PERCENTAGE("%");

    private final String label;

    DrugPotencyUnit(String label) { this.label = label; }

}
