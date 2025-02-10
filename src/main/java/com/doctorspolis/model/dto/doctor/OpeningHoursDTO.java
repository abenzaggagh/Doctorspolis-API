package com.doctorspolis.model.dto.doctor;

import com.doctorspolis.model.enumuration.Day;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalTime;
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

    private Boolean hasBreak;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime openingTime;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime closingTime;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime breakTime;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime resumeTime;

}
