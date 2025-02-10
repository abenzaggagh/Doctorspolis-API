package com.doctorspolis.model.dto.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class LocationDTO implements Serializable {

    @NotNull
    private Float latitude;

    @NotNull
    private Float longitude;

    @NotNull
    private Integer radius;

}
