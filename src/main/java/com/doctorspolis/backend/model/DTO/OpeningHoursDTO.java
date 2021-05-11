package com.doctorspolis.backend.model.DTO;

import com.doctorspolis.backend.commun.AbstractDTO;
import com.doctorspolis.backend.model.enumeration.Day;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class OpeningHoursDTO extends AbstractDTO {

    private String day;

    private Date openingTime;
    private Date closingTime;

}
