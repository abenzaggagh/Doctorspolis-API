package com.doctorspolis.backend.model.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class OpeningHoursDTO {

    private String day;

    private Boolean opens;

    @JsonFormat(pattern="HH:mm:ss")
    private Date openingTime;
    @JsonFormat(pattern="HH:mm:ss")
    private Date closingTime;

}
