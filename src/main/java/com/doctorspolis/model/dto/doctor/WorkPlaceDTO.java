package com.doctorspolis.model.dto.doctor;

import com.doctorspolis.model.dto.AddressDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class WorkPlaceDTO {

    private AddressDTO address;

    private String institutionName;

    private Float latitude;

    private Float longitude;

    private Double distance;

}
