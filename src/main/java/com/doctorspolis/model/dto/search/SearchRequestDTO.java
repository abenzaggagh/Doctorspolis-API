package com.doctorspolis.model.dto.search;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SearchRequestDTO implements Serializable {

    private String query;

    private String speciality;

    private LocationDTO location;

    private String city;

}
