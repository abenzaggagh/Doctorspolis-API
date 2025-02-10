package com.doctorspolis.model.data.doctor;

import com.doctorspolis.utility.AbstractReferential;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Speciality extends AbstractReferential {

    @ElementCollection
    @MapKeyColumn(name="lang")
    @Column(name="value")
    @CollectionTable(
            name="speciality_i18n",
            joinColumns = @JoinColumn(name="speciality_id")
    )
    private Map<String, String> value;

    @ElementCollection
    @MapKeyColumn(name="lang")
    @Column(name="description")
    @CollectionTable(name="speciality_i18n",
            joinColumns = @JoinColumn(name="speciality_id"))
    private Map<String, String> description;

}
