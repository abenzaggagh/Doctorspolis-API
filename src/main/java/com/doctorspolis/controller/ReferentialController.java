package com.doctorspolis.controller;

import com.doctorspolis.model.dto.doctor.SpecialityDTO;
import com.doctorspolis.service.ReferentialService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.doctorspolis.utility.DoctorspolisConstants.*;

@RequiredArgsConstructor

@RestController
@RequestMapping(REFERENTIAL_ENDPOINT)
public class ReferentialController {

    private final ReferentialService referentialService;

    @GetMapping(SPECIALITIES_ENDPOINT)
    public List<SpecialityDTO> specialities() {
        return this.referentialService.specialities();
    }

    @GetMapping(SPECIALITY_ENDPOINT)
    public SpecialityDTO speciality(@RequestParam String code) {
        return this.referentialService.speciality(code);
    }



}
