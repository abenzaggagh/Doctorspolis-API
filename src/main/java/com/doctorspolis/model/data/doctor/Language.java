package com.doctorspolis.model.data.doctor;

import com.doctorspolis.utility.AbstractReferential;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Map;

@Data

@Entity
public class Language extends AbstractReferential {

    @ElementCollection
    @MapKeyColumn(name="lang")
    @Column(name="value")
    @CollectionTable(
            name="language_i18n",
            joinColumns = @JoinColumn(name="language_id")
    )
    private Map<String, String> value;

}
