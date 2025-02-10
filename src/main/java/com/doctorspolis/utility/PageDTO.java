package com.doctorspolis.utility;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Builder

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class PageDTO<T> {

    private Set<T> result = new HashSet<>();

    private Integer pageNumber = 0;

    private Integer pageSize = 0;

    private Integer total;

    private Boolean hasNextPage = false;

}
